package com.example.ding.umutos.objects;

public class Category {
    private String[] category;

    public Category(){
        category = new String[]{"ALL","Agriculture", "Architecture and design", "Business","Biology", "Computer Science","Divinity", "Education", "Engineering and technology", "Environmental studies and forestry", "Family and consumer science", "Human physical performance and recreation", "Journalism, media studies and communication", "Law", "Library and museum studies", "Medicine", "Military sciences", "Public administration", "Public policy", "Social work", "Transportation"};
    }

    public String[] getCategory(){
        return category;
    }

}
