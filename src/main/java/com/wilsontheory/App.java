package com.wilsontheory;

import com.google.gson.Gson;
import com.wilsontheory.AddressBookProtos.Person;
import com.wilsontheory.AddressBookProtos.AddressBook;

import java.io.IOException;
import java.util.Arrays;


public class App {

    public static void main(String[] args) {
        final int TEST_ITERATIONS = 100;

        AddressBook ab = AddressBook.newBuilder()
                .addPeople(
                        Person.newBuilder()
                                .setId(1234)
                                .setName("John Doe")
                                .setEmail("jdoe@example.com")
                                .addPhones(
                                        Person.PhoneNumber.newBuilder()
                                                .setNumber("555-4321")
                                                .setType(Person.PhoneType.HOME))
                )
                .build();

        String jsonAddress = "{\n" +
                "  \"people\": [{" +
                        "  \"id\": 1234,\n" +
                        "  \"name\": \"John Doe\",\n" +
                        "  \"phones\": [{\"number\": \"555-4321\", \"type\": 1}]," +
                        "  \"email\": \"jdoe@example.com\"" +
                    "}]" +
                "}";

        byte[] protobuffBytesAddressBook = ab.toByteArray();
        byte[] jsonBytesAddressBook = jsonAddress.getBytes();

        Person p = Person.newBuilder()
                .setId(1234)
                .setName("Jane Doe")
                .setEmail("jdoe@example.com")
                .addPhones(
                        Person.PhoneNumber.newBuilder()
                        .setNumber("123-123")
                        .setType(Person.PhoneType.HOME)
                )
                .build();

        String janeJson = "{" +
                    "\"id\": 1234," +
                    "\"name\": \"Jane Doe\"," +
                    "\"email\": \"jdoe@example.com\"," +
                    "  \"phones\": [{\"number\": \"123-123\", \"type\": 1}]" +
                "}";

        byte[] protobuffBytesPerson = p.toByteArray();
        byte[] jsonBytesPerson = janeJson.getBytes();

//        System.out.println("protobuf object bytes: " + protobuffBytesAddressBook.length);
//        System.out.println(Arrays.toString(protobuffBytesAddressBook));
//        System.out.println("json object bytes: " + jsonBytesAddressBook.length);
//        System.out.println(Arrays.toString(jsonBytesAddressBook));


        long start1 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            try {
                getEmailFromSerializedProtobufAddressBook(protobuffBytesAddressBook);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long time1 = (System.nanoTime() - start1) / 1000000;

        long start2 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            getEmailFromSerializedJsonAddressBook(jsonBytesAddressBook);
        }
        long time2 = (System.nanoTime() - start2) / 1000000;

        System.out.println("EXPERIMENT 1: Address Book deserialization");
        System.out.println("protobuf: " + protobuffBytesAddressBook.length + "bytes, json: " + jsonBytesAddressBook.length + "bytes");
        System.out.println("protobuf: " + time1);
        System.out.println("json: " + time2);
        long diff = time2 - time1;
        System.out.println("protobuf cumulatively faster by : " + diff + "ms, ratio: " + (float) time2 / time1);


        System.out.println("\n---\n");

        long start3 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            try {
                getEmailFromSerializedProtobufPerson(protobuffBytesPerson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long time3 = (System.nanoTime() - start3) / 1000000;

        long start4 = System.nanoTime();
        for (int n=1; n<TEST_ITERATIONS; n++){
            getEmailFromSerializedJsonPerson(jsonBytesPerson);
        }
        long time4 = (System.nanoTime() - start4) / 1000000;

        System.out.println("EXPERIMENT 2: Person deserialization");
        System.out.println("protobuf: " + protobuffBytesPerson.length + "bytes, json: " + jsonBytesPerson.length + "bytes");
        System.out.println("protobuf: " + time3);
        System.out.println("json: " + time4);
        diff = time4 - time3;
        System.out.println("protobuf cumulatively faster by : " + diff + "ms, ratio: " + (float) time4 / time3);

    }

    public static String getEmailFromSerializedProtobufAddressBook(byte[] data) throws IOException {
        return AddressBook.parseFrom(data).getPeople(0).getEmail();
    }

    public static String getEmailFromSerializedJsonAddressBook(byte[] data) {
        return new Gson().fromJson(new String(data), AddressBook2.class).people.get(0).getEmail();
    }

    public static String getEmailFromSerializedProtobufPerson(byte[] data) throws IOException {
        return Person.parseFrom(data).getEmail();
    }

    public static String getEmailFromSerializedJsonPerson(byte[] data) {
        return new Gson().fromJson(new String(data), AddressBook2.Person.class).getEmail();
    }


}
