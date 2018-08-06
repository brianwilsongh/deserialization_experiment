package com.wilsontheory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wilsontheory.PersonOuterClass.Person;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        final int TEST_ITERATIONS = 10;

//        AddressBook ab = AddressBook.newBuilder()
//                .addPeople(
//                        Person.newBuilder()
//                                .setId(1234)
//                                .setName("John Doe")
//                                .setEmail("jdoe@example.com")
//                                .addPhones(
//                                        Person.PhoneNumber.newBuilder()
//                                                .setNumber("555-4321")
//                                                .setType(Person.PhoneType.HOME))
//                )
//                .build();
//
//        String jsonAddress = "{\n" +
//                "  \"people\": [{" +
//                        "  \"id\": 1234,\n" +
//                        "  \"name\": \"John Doe\",\n" +
//                        "  \"phones\": [{\"number\": \"555-4321\", \"type\": 1}]," +
//                        "  \"email\": \"jdoe@example.com\"" +
//                    "}]" +
//                "}";


        Person p1 = Person.newBuilder()
                .setId(1234)
                .setName("Jane Doe")
                .setEmail("janedoe@example.com")
                .addPhones(
                        Person.PhoneNumber.newBuilder()
                        .setNumber("123-1234")
                        .setType(Person.PhoneType.HOME)
                )
                .build();


        PersonModel p2 = new PersonModel()
                .setId(4321)
                .setName("John Doe")
                .setEmail("johndoe@example.com");
        p2.addNumber(new Number("555-4321", Number.PhoneType.HOME));

        System.out.println(Arrays.toString(p1.toByteArray()));
        System.out.println(Arrays.toString(new Gson().toJson(p2).getBytes()));


        long start1 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            try {
                getEmailFromSerializedProtobufPerson(p1.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long time1 = (System.nanoTime() - start1) / 1000000;

        long start2 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            try {
                getEmailFromSerializedJsonPerson(new Gson().toJson(p2));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        long time2 = (System.nanoTime() - start2) / 1000000;

        System.out.println("EXPERIMENT 1: Person deserialization");
        System.out.println("protobuf time to completion (ms): " + time1);
        System.out.println("json time to completion (ms): " + time2);
        long diff = time2 - time1;
        System.out.println("protobuf cumulatively faster by : " + diff + "ms, ratio: " + (float) time2 / time1);

        

        //network call
//        final String IP_ADDRESS = "";
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet g = new HttpGet(IP_ADDRESS);
//        HttpPost post = new HttpPost(IP_ADDRESS);
//        post.setHeader("Content-type", "application/octet-stream");
//        HttpEntity ent = new ByteArrayEntity(p.toByteArray());
//        post.setEntity(ent);
//        System.out.println("before sending (len)" +  p.toByteArray().length);
//        System.out.println("before sending" +  Arrays.toString(p.toByteArray()));
//        try {
//            CloseableHttpResponse response = client.execute(g);
//            HttpEntity entity = response.getEntity();
//            Scanner scanner = new Scanner(entity.getContent()).useDelimiter("\\A");
//            System.out.println(scanner.hasNext() ? scanner.next() : "");
//            response.close();
//
//            CloseableHttpResponse response2 = client.execute(post);
//            entity = response2.getEntity();
//            System.out.println("return byte (len): " + IOUtils.toByteArray(entity.getContent()).length);
//            System.out.println("returned byte array len: " + Arrays.toString(IOUtils.toByteArray(entity.getContent())));
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }


    public static String getEmailFromSerializedProtobufPerson(byte[] data) throws IOException {
        return Person.parseFrom(data).getEmail();
    }

    public static String getEmailFromSerializedJsonPerson(String data) {
        return new Gson().fromJson(data, PersonModel.class).getEmail();
    }





}
