import Bean.Course;
import Bean.Person;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import com.alibaba.fastjson2.TypeReference;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @Author: whwu6
 * @Date:2022/4/27} 16:24
 */
public class FastJsonTest_1 {

    public static void main(String[] args) {
        String testJsonStr = (String) createTestData("JsonStr");
        Course javaObject = (Course) createTestData("JavaObject");

        // Java对象 转 json字符串
        // ObjectToJsonStr();

        // json字符串 转 Json 对象
        // JsonStrToJsonObject(testJsonStr);

        //json字符串 转 java对象
        // JsonStrToJavaObject(testJsonStr);

        //json对象和java对象转化
        JsonObjectandJavaObject(javaObject);
    }




    /**
     * @return java.lang.Object
     * @Description //测试数据生成
     * @Date 14:17 2022/4/28
     * @Param [returnType] "JsonStr" or "JavaObject"
     * @Author whwu6
     **/
    private static Object createTestData(String returnType) {
        Person[] stds = {
                new Person("Jhon", 20),
                new Person("Bob", 22),
                new Person("Jason", 24)
        };
        List<Person> people = Arrays.asList(stds);
        Course course = new Course("computer", people);
        String testStr = JSON.toJSONString(course);
        // System.out.println(testStr);
        if (returnType.equals("JsonStr")) {
            return testStr;
        } else if (returnType.equals("JavaObject")) {
            return course;
        } else {
            return null;
        }
    }

    /**
     * Java Object convert Json String
     */
    private static void ObjectToJsonStr() {
        // Object ==> json string
        Person p1 = new Person("p2", 20);
        String s = JSON.toJSONString(p1);
        System.out.println(s); //{"age":20,"name":"p2"}

        // List<Object> ==> json string
        ArrayList<Object> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(new Person("p1", 30));
        String s1 = JSON.toJSONString(persons);
        System.out.println(s1); //[{"age":20,"name":"p2"},{"age":30,"name":"p1"}]

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("age");

        //complex data transition
        Course computer = new Course("computer", p1);
        String s2 = com.alibaba.fastjson.JSON.toJSONString(computer);
        System.out.println(s2); //{"courseName":"computer","stus":{"age":20,"name":"p2"}}

        String s3 = JSON.toJSONString(computer, filter);
        System.out.println(s3); //{"courseName":"computer","stus":{"name":"p2"}}
    }

    /**
     * JsonStr convert to Json Object
     */
    private static void JsonStrToJsonObject(String testStr) {

        //{"courseName":"computer","stus":[{"age":20,"name":"Jhon"},{"age":22,"name":"Bob"},{"age":24,"name":"Jason"}]}

        // 了解get getString getInteger 等这些都是返回值类型 避免强转.
        JSONObject jsonObject = JSON.parseObject(testStr);
        String courseName = (String) jsonObject.get("courseName"); //computer
        JSONArray stus = (JSONArray) jsonObject.get("stus");//JSONArray
        JSONObject jsobj2 = (JSONObject) stus.get(2);//JSONObject
        jsobj2.get("age"); //24

        // 复杂格式的 使用 getJSONArray() 或者getJSONObject() ,也可避免强转
        JSONArray stus1 = jsonObject.getJSONArray("stus");
        System.out.println(stus1); //[{"age":20,"name":"Jhon"},{"age":22,"name":"Bob"},{"age":24,"name":"Jason"}]
    }

    /**
     * JsonStr convert to Java Object
     */
    private static void JsonStrToJavaObject(String testJsonStr) {
        // System.out.println(testJsonStr);
        //{"courseName":"computer","stus":[{"age":20,"name":"Jhon"},{"age":22,"name":"Bob"},{"age":24,"name":"Jason"}]}
        // Course course = JSON.parseObject(testJsonStr, Course.class);
    }

    // 还有一种 JSON对象和Java 对象的转换
    /**
     * Json Object to Java Object
     */

    private static void JsonObjectandJavaObject(Course javaObject) {

        Object o = JSON.toJSON(javaObject);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(javaObject);
        Course course = JSON.toJavaObject(jsonObject, Course.class);
    }


}
