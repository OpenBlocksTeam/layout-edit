package com.openblocks.module.layoutedit;

public class LWidgetProperty {

    String name;
    Object value;
    LWidgetPropertyType property_type;

    public LWidgetProperty(String name, Object value, LWidgetPropertyType property_type) {
        this.name = name;
        this.value = value;
        this.property_type = property_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public LWidgetPropertyType getPropertyType() {
        return property_type;
    }
}
