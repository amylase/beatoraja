//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.10 at 05:22:11 PM JST 
//


package bms.player.lunaticrave2.ir;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}scorelist"/>
 *         &lt;element ref="{}rivalname"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "scorelist",
    "rivalname"
})
@XmlRootElement(name = "lr2ir")
public class Lr2Ir {

    @XmlElement(required = true)
    protected Scorelist scorelist;
    @XmlElement(required = true)
    protected String rivalname;

    /**
     * Gets the value of the scorelist property.
     * 
     * @return
     *     possible object is
     *     {@link Scorelist }
     *     
     */
    public Scorelist getScorelist() {
        return scorelist;
    }

    /**
     * Sets the value of the scorelist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scorelist }
     *     
     */
    public void setScorelist(Scorelist value) {
        this.scorelist = value;
    }

    /**
     * Gets the value of the rivalname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRivalname() {
        return rivalname;
    }

    /**
     * Sets the value of the rivalname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRivalname(String value) {
        this.rivalname = value;
    }

}
