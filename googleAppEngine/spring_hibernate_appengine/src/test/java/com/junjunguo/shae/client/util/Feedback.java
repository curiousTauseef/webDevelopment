package com.junjunguo.shae.client.util;

/**
 * This file is part of spring_hibernate_relation.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 30/10/15.
 */
public class Feedback {
    private String description;
    private String methodName;
    private String className;


    public Feedback(String description, String methodName, String className) {
        this.description = description;
        this.methodName = methodName;
        this.className = className;
    }

    @Override
    public String toString() {

        return "Feedback{" +
               "description: '" + description + '\'' +
               ", methodName: '" + methodName + '\'' +
               ", className=: '" + className + '\'' +
               '}';
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() { return description; }

    /**
     * Gets methodName.
     *
     * @return Value of methodName.
     */
    public String getMethodName() { return methodName; }

    /**
     * Sets new methodName.
     *
     * @param methodName New value of methodName.
     */
    public void setMethodName(String methodName) { this.methodName = methodName; }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Sets new className.
     *
     * @param className New value of className.
     */
    public void setClassName(String className) { this.className = className; }

    /**
     * Gets className.
     *
     * @return Value of className.
     */
    public String getClassName() { return className; }
}
