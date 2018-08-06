package com.wilsontheory;

class Number {
    public Number(String number, PhoneType type){
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public Number setNumber(String number) {
        this.number = number;
        return this;
    }

    private String number;

    public PhoneType getType() {
        return type;
    }

    public Number setType(PhoneType type) {
        this.type = type;
        return this;
    }

    public enum PhoneType {
        MOBILE(0),
        HOME(1),
        WORK(2);

        private int value;

        private PhoneType(int val){
            this.value = val;
        }

        public int getValue(){
            return value;
        }
    }

    private PhoneType type;
}