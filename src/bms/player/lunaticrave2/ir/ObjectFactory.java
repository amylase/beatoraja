//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.10 at 05:22:11 PM JST 
//


package bms.player.lunaticrave2.ir;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hash_QNAME = new QName("", "hash");
    private final static QName _Gd_QNAME = new QName("", "gd");
    private final static QName _Combo_QNAME = new QName("", "combo");
    private final static QName _Minbp_QNAME = new QName("", "minbp");
    private final static QName _Rivalname_QNAME = new QName("", "rivalname");
    private final static QName _Clear_QNAME = new QName("", "clear");
    private final static QName _Lastupdate_QNAME = new QName("", "lastupdate");
    private final static QName _Pg_QNAME = new QName("", "pg");
    private final static QName _Notes_QNAME = new QName("", "notes");
    private final static QName _Gr_QNAME = new QName("", "gr");
    private final static QName _Bd_QNAME = new QName("", "bd");
    private final static QName _Pr_QNAME = new QName("", "pr");
    private final static QName _Option_QNAME = new QName("", "option");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Score }
     * 
     */
    public Score createScore() {
        return new Score();
    }

    /**
     * Create an instance of {@link Lr2Ir }
     * 
     */
    public Lr2Ir createLr2Ir() {
        return new Lr2Ir();
    }

    /**
     * Create an instance of {@link Scorelist }
     * 
     */
    public Scorelist createScorelist() {
        return new Scorelist();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "hash")
    public JAXBElement<String> createHash(String value) {
        return new JAXBElement<String>(_Hash_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gd")
    public JAXBElement<BigInteger> createGd(BigInteger value) {
        return new JAXBElement<BigInteger>(_Gd_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "combo")
    public JAXBElement<BigInteger> createCombo(BigInteger value) {
        return new JAXBElement<BigInteger>(_Combo_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "minbp")
    public JAXBElement<BigInteger> createMinbp(BigInteger value) {
        return new JAXBElement<BigInteger>(_Minbp_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rivalname")
    public JAXBElement<String> createRivalname(String value) {
        return new JAXBElement<String>(_Rivalname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "clear")
    public JAXBElement<BigInteger> createClear(BigInteger value) {
        return new JAXBElement<BigInteger>(_Clear_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lastupdate")
    public JAXBElement<BigInteger> createLastupdate(BigInteger value) {
        return new JAXBElement<BigInteger>(_Lastupdate_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pg")
    public JAXBElement<BigInteger> createPg(BigInteger value) {
        return new JAXBElement<BigInteger>(_Pg_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "notes")
    public JAXBElement<BigInteger> createNotes(BigInteger value) {
        return new JAXBElement<BigInteger>(_Notes_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gr")
    public JAXBElement<BigInteger> createGr(BigInteger value) {
        return new JAXBElement<BigInteger>(_Gr_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bd")
    public JAXBElement<BigInteger> createBd(BigInteger value) {
        return new JAXBElement<BigInteger>(_Bd_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pr")
    public JAXBElement<BigInteger> createPr(BigInteger value) {
        return new JAXBElement<BigInteger>(_Pr_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "option")
    public JAXBElement<BigInteger> createOption(BigInteger value) {
        return new JAXBElement<BigInteger>(_Option_QNAME, BigInteger.class, null, value);
    }

}
