/**
 * 
 */
package com.ats.test.model;

/**
 * Search criteria object.
 * Used ad request body parameter into search rest API
 * @author Adondoni
 */
public class SearchCriteria
{
    String text;

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}
    
}
