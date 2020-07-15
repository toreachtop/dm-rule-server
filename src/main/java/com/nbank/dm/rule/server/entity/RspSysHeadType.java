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
@XmlType(name = "RspSysHeadType", namespace = "http://esb.dcitsbiz.com/metadata", propOrder = {
        "pkgLength",
        "reqSeq",
        "serviceID",
        "channelID",
        "legOrgID",
        "reqDate",
        "reqTime",
        "mac",
        "version",
        "priority",
        "reqSysID",
        "domainRef",
        "acceptLang",
        "globalSeq",
        "orgSysID",
        "respSeq",
        "respDate",
        "respTime",
        "transStatus",
        "retCode",
        "retMsg",
        "respSysID",
        "trackCode",
        "reseFlg",
        "reseFld",
        "svcScn",
        "tmlCd"
})
public class RspSysHeadType {

    @XmlElement(name = "PkgLength")
    private String pkgLength;

    @XmlElement(name = "ReqSeq", required = true)
    private String reqSeq;

    @XmlElement(name = "ServiceID", required = true)
    private String serviceID;

    @XmlElement(name = "ChannelID", required = true)
    private String channelID;

    @XmlElement(name = "LegOrgID", required = true)
    private String legOrgID;

    @XmlElement(name = "ReqDate", required = true)
    private String reqDate;

    @XmlElement(name = "ReqTime", required = true)
    private String reqTime;

    @XmlElement(name = "MAC")
    private String mac;

    @XmlElement(name = "Version", required = true)
    private String version;

    @XmlElement(name = "Priority")
    private String priority;

    @XmlElement(name = "ReqSysID")
    private String reqSysID;

    @XmlElement(name = "DomainRef")
    private String domainRef;

    @XmlElement(name = "AcceptLang")
    private String acceptLang;

    @XmlElement(name = "GlobalSeq")
    private String globalSeq;

    @XmlElement(name = "OrgSysID")
    private String orgSysID;

    @XmlElement(name = "RespSeq")
    private String respSeq;

    @XmlElement(name = "RespDate")
    private String respDate;

    @XmlElement(name = "RespTime")
    private String respTime;

    @XmlElement(name = "TransStatus")
    private String transStatus;

    @XmlElement(name = "RetCode")
    private String retCode;

    @XmlElement(name = "RetMsg")
    private String retMsg;

    @XmlElement(name = "RespSysID")
    private String respSysID;

    @XmlElement(name = "TrackCode")
    private String trackCode;

    @XmlElement(name = "ReseFlg")
    private String reseFlg;

    @XmlElement(name = "ReseFld")
    private String reseFld;

    @XmlElement(name = "SvcScn")
    private String svcScn;

    @XmlElement(name = "TmlCd")
    private String tmlCd;
}
