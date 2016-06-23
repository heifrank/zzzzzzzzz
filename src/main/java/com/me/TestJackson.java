package com.me;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by heifrank on 16/3/12.
 */
public class TestJackson {
    public static void main(String[] args) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        User user = mapper.readValue(new File("/Users/heifrank/codes/test.json"), User.class);
//        mapper.readValue(new File("/Users/heifrank/codes/test.json"), Collection.class);
        Immu imm = new Immu(100);
        ObjectMapper mapper = new ObjectMapper();
        String deserialization = mapper.writeValueAsString(imm);

        Immu imm2 = mapper.readValue(deserialization, Immu.class);
        System.out.println(imm2.getIntValue());
    }
}

class Immu{
    @JsonCreator
    public Immu(@JsonProperty("value") int val){
        this.intValue = val;
    }

    public int getIntValue(){
        return this.intValue;
    }

    private final int intValue;
}

class User {
    public enum Gender { MALE, FEMALE };

    public static class Name {
        private String _first, _last;

        public String getFirst() { return _first; }
        public String getLast() { return _last; }

        public void setFirst(String s) { _first = s; }
        public void setLast(String s) { _last = s; }
    }

    private Gender _gender;
    private Name _name;
    private boolean _isVerified;
    private byte[] _userImage;

    public Name getName() { return _name; }
    public boolean isVerified() { return _isVerified; }
    public Gender getGender() { return _gender; }
    public byte[] getUserImage() { return _userImage; }

    public void setName(Name n) { _name = n; }
    public void setVerified(boolean b) { _isVerified = b; }
    public void setGender(Gender g) { _gender = g; }
    public void setUserImage(byte[] b) { _userImage = b; }
}