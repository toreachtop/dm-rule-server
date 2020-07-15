package com.nbank.dm.rule.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RspAppHeadType", namespace = "http://esb.dcitsbiz.com/metadata", propOrder = {
        "tlrNo",
        "brId"
})
public class RspAppHeadType {

    @XmlElement(name = "TlrNo")
    private String tlrNo;

    @XmlElement(name = "BrId")
    private String brId;
}
