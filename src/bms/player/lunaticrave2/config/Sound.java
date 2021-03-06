//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.12 at 11:34:54 AM JST 
//


package bms.player.lunaticrave2.config;

import java.math.BigInteger;
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
 *         &lt;element ref="{}bufferlength"/>
 *         &lt;element ref="{}numbuffers"/>
 *         &lt;element ref="{}disabledsp"/>
 *         &lt;element ref="{}output"/>
 *         &lt;element ref="{}driver"/>
 *         &lt;element ref="{}disablefmod"/>
 *         &lt;element ref="{}volumeflag"/>
 *         &lt;element ref="{}volumebgm"/>
 *         &lt;element ref="{}volumekey"/>
 *         &lt;element ref="{}volumemaster"/>
 *         &lt;element ref="{}eqflag"/>
 *         &lt;element ref="{}eqp0"/>
 *         &lt;element ref="{}eqp1"/>
 *         &lt;element ref="{}eqp2"/>
 *         &lt;element ref="{}eqp3"/>
 *         &lt;element ref="{}eqp4"/>
 *         &lt;element ref="{}eqp5"/>
 *         &lt;element ref="{}eqp6"/>
 *         &lt;element ref="{}pitchflag"/>
 *         &lt;element ref="{}pitchtype"/>
 *         &lt;element ref="{}pitchp"/>
 *         &lt;element ref="{}fxflag_0"/>
 *         &lt;element ref="{}fxtype_0"/>
 *         &lt;element ref="{}fxtarget_0"/>
 *         &lt;element ref="{}fxp1_0"/>
 *         &lt;element ref="{}fxp2_0"/>
 *         &lt;element ref="{}fxflag_1"/>
 *         &lt;element ref="{}fxtype_1"/>
 *         &lt;element ref="{}fxtarget_1"/>
 *         &lt;element ref="{}fxp1_1"/>
 *         &lt;element ref="{}fxp2_1"/>
 *         &lt;element ref="{}fxflag_2"/>
 *         &lt;element ref="{}fxtype_2"/>
 *         &lt;element ref="{}fxtarget_2"/>
 *         &lt;element ref="{}fxp1_2"/>
 *         &lt;element ref="{}fxp2_2"/>
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
    "bufferlength",
    "numbuffers",
    "disabledsp",
    "output",
    "driver",
    "disablefmod",
    "volumeflag",
    "volumebgm",
    "volumekey",
    "volumemaster",
    "eqflag",
    "eqp0",
    "eqp1",
    "eqp2",
    "eqp3",
    "eqp4",
    "eqp5",
    "eqp6",
    "pitchflag",
    "pitchtype",
    "pitchp",
    "fxflag0",
    "fxtype0",
    "fxtarget0",
    "fxp10",
    "fxp20",
    "fxflag1",
    "fxtype1",
    "fxtarget1",
    "fxp11",
    "fxp21",
    "fxflag2",
    "fxtype2",
    "fxtarget2",
    "fxp12",
    "fxp22"
})
@XmlRootElement(name = "sound")
public class Sound {

    @XmlElement(required = true)
    protected BigInteger bufferlength;
    @XmlElement(required = true)
    protected BigInteger numbuffers;
    @XmlElement(required = true)
    protected BigInteger disabledsp;
    @XmlElement(required = true)
    protected BigInteger output;
    @XmlElement(required = true)
    protected BigInteger driver;
    @XmlElement(required = true)
    protected BigInteger disablefmod;
    @XmlElement(required = true)
    protected BigInteger volumeflag;
    @XmlElement(required = true)
    protected BigInteger volumebgm;
    @XmlElement(required = true)
    protected BigInteger volumekey;
    @XmlElement(required = true)
    protected BigInteger volumemaster;
    @XmlElement(required = true)
    protected BigInteger eqflag;
    @XmlElement(required = true)
    protected BigInteger eqp0;
    @XmlElement(required = true)
    protected BigInteger eqp1;
    @XmlElement(required = true)
    protected BigInteger eqp2;
    @XmlElement(required = true)
    protected BigInteger eqp3;
    @XmlElement(required = true)
    protected BigInteger eqp4;
    @XmlElement(required = true)
    protected BigInteger eqp5;
    @XmlElement(required = true)
    protected BigInteger eqp6;
    @XmlElement(required = true)
    protected BigInteger pitchflag;
    @XmlElement(required = true)
    protected BigInteger pitchtype;
    @XmlElement(required = true)
    protected BigInteger pitchp;
    @XmlElement(name = "fxflag_0", required = true)
    protected BigInteger fxflag0;
    @XmlElement(name = "fxtype_0", required = true)
    protected BigInteger fxtype0;
    @XmlElement(name = "fxtarget_0", required = true)
    protected BigInteger fxtarget0;
    @XmlElement(name = "fxp1_0", required = true)
    protected BigInteger fxp10;
    @XmlElement(name = "fxp2_0", required = true)
    protected BigInteger fxp20;
    @XmlElement(name = "fxflag_1", required = true)
    protected BigInteger fxflag1;
    @XmlElement(name = "fxtype_1", required = true)
    protected BigInteger fxtype1;
    @XmlElement(name = "fxtarget_1", required = true)
    protected BigInteger fxtarget1;
    @XmlElement(name = "fxp1_1", required = true)
    protected BigInteger fxp11;
    @XmlElement(name = "fxp2_1", required = true)
    protected BigInteger fxp21;
    @XmlElement(name = "fxflag_2", required = true)
    protected BigInteger fxflag2;
    @XmlElement(name = "fxtype_2", required = true)
    protected BigInteger fxtype2;
    @XmlElement(name = "fxtarget_2", required = true)
    protected BigInteger fxtarget2;
    @XmlElement(name = "fxp1_2", required = true)
    protected BigInteger fxp12;
    @XmlElement(name = "fxp2_2", required = true)
    protected BigInteger fxp22;

    /**
     * Gets the value of the bufferlength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBufferlength() {
        return bufferlength;
    }

    /**
     * Sets the value of the bufferlength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBufferlength(BigInteger value) {
        this.bufferlength = value;
    }

    /**
     * Gets the value of the numbuffers property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumbuffers() {
        return numbuffers;
    }

    /**
     * Sets the value of the numbuffers property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumbuffers(BigInteger value) {
        this.numbuffers = value;
    }

    /**
     * Gets the value of the disabledsp property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisabledsp() {
        return disabledsp;
    }

    /**
     * Sets the value of the disabledsp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisabledsp(BigInteger value) {
        this.disabledsp = value;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOutput() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOutput(BigInteger value) {
        this.output = value;
    }

    /**
     * Gets the value of the driver property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDriver() {
        return driver;
    }

    /**
     * Sets the value of the driver property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDriver(BigInteger value) {
        this.driver = value;
    }

    /**
     * Gets the value of the disablefmod property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisablefmod() {
        return disablefmod;
    }

    /**
     * Sets the value of the disablefmod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisablefmod(BigInteger value) {
        this.disablefmod = value;
    }

    /**
     * Gets the value of the volumeflag property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolumeflag() {
        return volumeflag;
    }

    /**
     * Sets the value of the volumeflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolumeflag(BigInteger value) {
        this.volumeflag = value;
    }

    /**
     * Gets the value of the volumebgm property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolumebgm() {
        return volumebgm;
    }

    /**
     * Sets the value of the volumebgm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolumebgm(BigInteger value) {
        this.volumebgm = value;
    }

    /**
     * Gets the value of the volumekey property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolumekey() {
        return volumekey;
    }

    /**
     * Sets the value of the volumekey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolumekey(BigInteger value) {
        this.volumekey = value;
    }

    /**
     * Gets the value of the volumemaster property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVolumemaster() {
        return volumemaster;
    }

    /**
     * Sets the value of the volumemaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVolumemaster(BigInteger value) {
        this.volumemaster = value;
    }

    /**
     * Gets the value of the eqflag property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqflag() {
        return eqflag;
    }

    /**
     * Sets the value of the eqflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqflag(BigInteger value) {
        this.eqflag = value;
    }

    /**
     * Gets the value of the eqp0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp0() {
        return eqp0;
    }

    /**
     * Sets the value of the eqp0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp0(BigInteger value) {
        this.eqp0 = value;
    }

    /**
     * Gets the value of the eqp1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp1() {
        return eqp1;
    }

    /**
     * Sets the value of the eqp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp1(BigInteger value) {
        this.eqp1 = value;
    }

    /**
     * Gets the value of the eqp2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp2() {
        return eqp2;
    }

    /**
     * Sets the value of the eqp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp2(BigInteger value) {
        this.eqp2 = value;
    }

    /**
     * Gets the value of the eqp3 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp3() {
        return eqp3;
    }

    /**
     * Sets the value of the eqp3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp3(BigInteger value) {
        this.eqp3 = value;
    }

    /**
     * Gets the value of the eqp4 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp4() {
        return eqp4;
    }

    /**
     * Sets the value of the eqp4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp4(BigInteger value) {
        this.eqp4 = value;
    }

    /**
     * Gets the value of the eqp5 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp5() {
        return eqp5;
    }

    /**
     * Sets the value of the eqp5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp5(BigInteger value) {
        this.eqp5 = value;
    }

    /**
     * Gets the value of the eqp6 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEqp6() {
        return eqp6;
    }

    /**
     * Sets the value of the eqp6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEqp6(BigInteger value) {
        this.eqp6 = value;
    }

    /**
     * Gets the value of the pitchflag property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPitchflag() {
        return pitchflag;
    }

    /**
     * Sets the value of the pitchflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPitchflag(BigInteger value) {
        this.pitchflag = value;
    }

    /**
     * Gets the value of the pitchtype property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPitchtype() {
        return pitchtype;
    }

    /**
     * Sets the value of the pitchtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPitchtype(BigInteger value) {
        this.pitchtype = value;
    }

    /**
     * Gets the value of the pitchp property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPitchp() {
        return pitchp;
    }

    /**
     * Sets the value of the pitchp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPitchp(BigInteger value) {
        this.pitchp = value;
    }

    /**
     * Gets the value of the fxflag0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxflag0() {
        return fxflag0;
    }

    /**
     * Sets the value of the fxflag0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxflag0(BigInteger value) {
        this.fxflag0 = value;
    }

    /**
     * Gets the value of the fxtype0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtype0() {
        return fxtype0;
    }

    /**
     * Sets the value of the fxtype0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtype0(BigInteger value) {
        this.fxtype0 = value;
    }

    /**
     * Gets the value of the fxtarget0 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtarget0() {
        return fxtarget0;
    }

    /**
     * Sets the value of the fxtarget0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtarget0(BigInteger value) {
        this.fxtarget0 = value;
    }

    /**
     * Gets the value of the fxp10 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp10() {
        return fxp10;
    }

    /**
     * Sets the value of the fxp10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp10(BigInteger value) {
        this.fxp10 = value;
    }

    /**
     * Gets the value of the fxp20 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp20() {
        return fxp20;
    }

    /**
     * Sets the value of the fxp20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp20(BigInteger value) {
        this.fxp20 = value;
    }

    /**
     * Gets the value of the fxflag1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxflag1() {
        return fxflag1;
    }

    /**
     * Sets the value of the fxflag1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxflag1(BigInteger value) {
        this.fxflag1 = value;
    }

    /**
     * Gets the value of the fxtype1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtype1() {
        return fxtype1;
    }

    /**
     * Sets the value of the fxtype1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtype1(BigInteger value) {
        this.fxtype1 = value;
    }

    /**
     * Gets the value of the fxtarget1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtarget1() {
        return fxtarget1;
    }

    /**
     * Sets the value of the fxtarget1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtarget1(BigInteger value) {
        this.fxtarget1 = value;
    }

    /**
     * Gets the value of the fxp11 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp11() {
        return fxp11;
    }

    /**
     * Sets the value of the fxp11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp11(BigInteger value) {
        this.fxp11 = value;
    }

    /**
     * Gets the value of the fxp21 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp21() {
        return fxp21;
    }

    /**
     * Sets the value of the fxp21 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp21(BigInteger value) {
        this.fxp21 = value;
    }

    /**
     * Gets the value of the fxflag2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxflag2() {
        return fxflag2;
    }

    /**
     * Sets the value of the fxflag2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxflag2(BigInteger value) {
        this.fxflag2 = value;
    }

    /**
     * Gets the value of the fxtype2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtype2() {
        return fxtype2;
    }

    /**
     * Sets the value of the fxtype2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtype2(BigInteger value) {
        this.fxtype2 = value;
    }

    /**
     * Gets the value of the fxtarget2 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxtarget2() {
        return fxtarget2;
    }

    /**
     * Sets the value of the fxtarget2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxtarget2(BigInteger value) {
        this.fxtarget2 = value;
    }

    /**
     * Gets the value of the fxp12 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp12() {
        return fxp12;
    }

    /**
     * Sets the value of the fxp12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp12(BigInteger value) {
        this.fxp12 = value;
    }

    /**
     * Gets the value of the fxp22 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFxp22() {
        return fxp22;
    }

    /**
     * Sets the value of the fxp22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFxp22(BigInteger value) {
        this.fxp22 = value;
    }

}
