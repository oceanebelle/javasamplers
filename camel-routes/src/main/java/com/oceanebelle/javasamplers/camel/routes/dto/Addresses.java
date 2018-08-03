package com.oceanebelle.javasamplers.camel.routes.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Addresses", propOrder = {"address"})
public class Addresses {
    protected List<Address> address;

    protected Addresses() {
        super();
    }

    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return address;
    }


    @Override
    public String toString() {
        return "Addresses{" +
                "address=" + address +
                '}';
    }
}
