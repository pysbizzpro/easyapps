package com.pysbizz.pdfreader.pdfreader.model;

public class VoterData {
    private String seqNumber;
    private String voterId;
    private String name;
    private String fatherName;
    private String houseNumber;
    private String sex;
    private String age;
	public String getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "VoterData [seqNumber=" + seqNumber + ", voterId=" + voterId + ", name=" + name + ", fatherName="
				+ fatherName + ", houseNumber=" + houseNumber + ", sex=" + sex + ", age=" + age + "]";
	}
     
}
 