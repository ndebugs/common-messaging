package com.debugs.messaging;

import com.debugs.messaging.lang.LObject;
import com.debugs.messaging.parser.LangParser;
import com.debugs.messaging.parser.TypeParser;
import com.debugs.messaging.type.TObject;
import com.debugs.messaging.utils.DateFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    private MessageBundle bundle;
    private Field field;
    private String value;
    
    @Before
    public void init() {
        Message temp = new MessageImpl();
        temp.set("tempKey1", "Temp Value 1");
        temp.set("tempKey2", "Temp Value 2");
        temp.set("tempKey3", "Temp Value 3");
        
        Message message = new MessageImpl();
        message.set("msgKey1", "Message Value 1");
        message.set("msgKey2", "Message Value 2");
        message.set("msgKey3", "Message Value 3");
        message.set("list", new String[] {"abc", "def", "ghi"});
        Map map = new HashMap();
        map.put("a", "123");
        map.put("b", "456");
        map.put("c", "789");
        message.set("map", map);
        
        bundle = new MessageBundle(temp, message);
        field = new FieldImpl();
        field.setId("Key");
        value = "Value";
    }
    
    @Test
    public void test() throws Exception {
        testType();
        testLang();
    }
    
    public void testType() throws Exception {
        System.out.println("--- TEST TYPE ---");
        TObject type = loadType("Boolean");
        parse(type, true, true);
        parse(type, "true", null);
        
        type = loadType("Character");
        parse(type, "a", 'a');
        parse(type, "", (char) 0);
        parse(type, "abc", null);
        
        type = loadType("String");
        parse(type, "abc123!@#", "abc123!@#");
        
        type = loadType("Integer");
        parse(type, "abc", null);
        parse(type, "123", 123);
        parse(type, "123.5", 123);
        
        type = loadType("Decimal");
        parse(type, "abc", null);
        parse(type, "123", 123.0);
        parse(type, "123.5", 123.5);
        
        type = loadType("DateTime(ddMMyyyy)");
        parse(type, "00000000", "00000000");
        parse(type, "31012013", "31012013");
        parse(type, "01312013", "01312013");
        
        type = loadType("List");
        String[] array1 = new String[] {};
        parse(type, array1, array1);
        String[] array2 = new String[] {"abc", "def"};
        parse(type, array2, array2);
        parse(type, "abc", null);
        
        type = loadType("Map");
        Map map1 = new HashMap();
        parse(type, map1, map1);
        Map map2 = new HashMap();
        map2.put("1", "a");
        map2.put("a", "a");
        map2.put("3", "3");
        parse(type, map2, map2);
        parse(type, "abc", null);
        
        System.out.println();
    }
    
    public void testLang() throws Exception {
        System.out.println("--- TEST LANG  ---");
        evaluate("EMPTY STRING",
                "",
                "[isEmpty:[message value]]");
        
        evaluate("STRING",
                "abc def \\[\\]\\:\\,\\\\",
                "[eq:[message value],abc def \\[\\]\\:\\,\\\\]");
        
        evaluate("SUBSTRING",
                "[string at:abcdef,0,3] [string at:abcdef,3] [string at:ghijkl,-4] [string at:ghijkl,-1,3]",
                "[eq:[message value],abc def ghi jkl]");
        
        evaluate("STRING MANIPULATION",
                "[string replace:A1B3C1 1d3e3f,\\[13\\],] [string lowCase:UvW] [string upCase:xYz]",
                "[eq:[message value],ABC def uvw XYZ]");
        
        evaluate("STRING PAD/UNPAD",
                "[string pad:123,0,-6] [string pad:456,0,6] [string trim:  ABC def  ] [string rTrim:123000,0] [string lTrim:000456,0]",
                "[eq:[message value],000123 456000 ABC def 123 456]");
        
        evaluate("STRING EVALUATION",
                "[string match:abc123,^\\[a-zA-Z0-9\\]*$] [string match:abc123!@#,^\\[a-zA-Z0-9\\]*$] [string contains:abc,c] [string length:abc]",
                "[eq:[message value],true false true 3]");
        
        evaluate("CHARACTER",
                "[char:65] [string charAt:abc,1]",
                "[eq:[message value],A b]");
        
        evaluate("NUMBER",
                "[int:123] [int:ff,16] [int:255,10,16] [dec:123] [dec:0.5]",
                "[eq:[message value],123 255 ff 123.0 0.5]");
        
        evaluate("CONDITION",
                "[if:[true],TRUE,FALSE] [and:[true],[false]] [or:[true],[false]] [xor:[true],[false]] [not:[true]]",
                "[eq:[message value],TRUE false true true false]");
        
        evaluate("COMPARATION",
                "[eq:[null],[temp get:0]] [neq:[int:1],1] [lt:2,3] [lte:3,2] [gt:3,2] [gte:3,3] [between:5,1,5]",
                "[eq:[message value],true false true false true true true]");
        
        evaluate("OBJECT EVALUATION",
                "[isNull:] [isEmpty:]",
                "[eq:[message value],false true]");
        
        evaluate("OBJECT COMPARATION",
                "[notNull:[null],] [notNull:abc,[null]] [notEmpty:[int:0],def] [notEmpty:[null],]",
                "[eq:[message value], abc def ]");
        
        evaluate("MATH OPERATION",
                "[math sum:1,1] [math sub:3,1] [math mul:2,2] [math div:10,3.0] [math mod:10,3]",
                "[eq:[message value],2 2 4 3.3333333333333335 1]");
        
        evaluate("MATH COMPARATION",
                "[math min:1,0] [math min:1,1.5] [math max:1,0] [math max:1,1.5]",
                "[eq:[message value],0 1.0 1 1.5]");
        
        evaluate("RANDOM",
                "[math random:1,9]",
                "[between:[message value],1,9]");
        
        DateFormatter formatter = new DateFormatter("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        evaluate("DATETIME",
                "[dateTime:dd-MM-yyyy] [dateTime:dd-MM-yyyy,-1D]",
                "[eq:[message value]," + formatter.format(cal.getTime()) + " " + formatter.format(cal, -1, DateFormatter.DAY) + "]");
        
        evaluate("LIST",
                "[list at:[list merge:[list:0,1,2],[string split:3\\,4\\,5,\\,]],3] [list join:[message get:list],-] [list contains:[message get:list],abc] [list size:[list new:5]]",
                "[eq:[message value],3 abc-def-ghi true 5]");
        
        evaluate("MAP",
                "[map get:[map:[entry:key1,value1],[entry:key2,value2],[entry:key3,value3]],key2] [map contains:[message get:map],123] [map size:[map new:5]] [map keys:[message get:map]] [map values:[message get:map]]",
                "[eq:[message value],value2 true 0 \\[a\\,b\\,c\\] \\[123\\,456\\,789\\]]");
        
        evaluate("MESSAGE",
                "[temp get:tempKey2] [message get:msgKey2] [message key] [message value]",
                "[eq:[message value],Temp Value 2 Message Value 2 Key Value]");
    }
    
    public TObject loadType(String data) throws Exception {
        System.out.println();
        System.out.println("plain      : " + data);
        
        TObject type = new TypeParser(data).parse();
        System.out.println("type       : " + type.alias());
        return type;
    }
    
    public Object parse(TObject type, Object value, Object expected) throws Exception {
        System.out.print("value: " + value + ", result: ");
        Object result = null;
        try {
            result = type.parse(value);
            System.out.println(type.parse(value));
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        Assert.assertEquals(result, expected);
        return result;
    }
    
    public Object evaluate(String title, String statement, String validator) throws Exception {
        System.out.println(title);
        System.out.println("statement          : " + statement);
        
        LObject lang = new LangParser(statement).parse();
        System.out.println("parsed stat.       : " + lang.toString());
        System.out.println("parsed stat. detail: " + lang.toVerbosedString());
        TObject result = lang.evaluate(bundle, field, value);
        System.out.println("result             : " + result);
        System.out.println();
        
        LObject langValidator = new LangParser(validator).parse();
        TObject resultValidator = langValidator.evaluate(null, null, result);
        Assert.assertEquals(resultValidator.getValue(), true);
        return result;
    }
}
