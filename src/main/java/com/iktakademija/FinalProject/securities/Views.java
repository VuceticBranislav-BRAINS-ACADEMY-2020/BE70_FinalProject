package com.iktakademija.FinalProject.securities;

public class Views {

	public static class Public {}
	public static class Student extends Public{}
	public static class Parent extends Student{}
	public static class Teacher extends Parent{}	
	public static class ClassMaster extends Teacher{}	
	public static class Admin extends ClassMaster{}	
	
}
