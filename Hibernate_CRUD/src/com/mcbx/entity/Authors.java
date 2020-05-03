package com.mcbx.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "authors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authors.findAll", query = "SELECT a FROM Authors a"),
    @NamedQuery(name = "Authors.findByIdauthors", query = "SELECT a FROM Authors a WHERE a.idauthors = :idauthors"),
    @NamedQuery(name = "Authors.findByGender", query = "SELECT a FROM Authors a WHERE a.gender = :gender"),
    @NamedQuery(name = "Authors.findByDateval", query = "SELECT a FROM Authors a WHERE a.dateval = :dateval"),
    @NamedQuery(name = "Authors.findByIntegerval", query = "SELECT a FROM Authors a WHERE a.integerval = :integerval"),
    @NamedQuery(name = "Authors.findByDoubleval", query = "SELECT a FROM Authors a WHERE a.doubleval = :doubleval")})
public class Authors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idauthors")
    private Integer idauthors;
    @Lob
    @Column(name = "firstname")
    private String firstname;
    @Lob
    @Column(name = "lastname")
    private String lastname;
    @Lob
    @Column(name = "contactinformation")
    private String contactinformation;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dateval")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateval;
    @Column(name = "integerval")
    private Integer integerval;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "doubleval")
    private Double doubleval;

    public Authors() {
    }

    public Authors(String firstname, String lastname, String contactinformation, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactinformation = contactinformation;
        this.gender = gender;
    }

    public Integer getIdauthors() {
        return idauthors;
    }

    public void setIdauthors(Integer idauthors) {
        this.idauthors = idauthors;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactinformation() {
        return contactinformation;
    }

    public void setContactinformation(String contactinformation) {
        this.contactinformation = contactinformation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateval() {
        return dateval;
    }

    public void setDateval(Date dateval) {
        this.dateval = dateval;
    }

    public Integer getIntegerval() {
        return integerval;
    }

    public void setIntegerval(Integer integerval) {
        this.integerval = integerval;
    }

    public Double getDoubleval() {
        return doubleval;
    }

    public void setDoubleval(Double doubleval) {
        this.doubleval = doubleval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idauthors != null ? idauthors.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authors)) {
            return false;
        }
        Authors other = (Authors) object;
        if ((this.idauthors == null && other.idauthors != null) || (this.idauthors != null && !this.idauthors.equals(other.idauthors))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Authors{" + "idauthors=" + idauthors + ", firstname=" + firstname + ", lastname=" + lastname + ", contactinformation=" + contactinformation + ", gender=" + gender + ", dateval=" + dateval + ", integerval=" + integerval + ", doubleval=" + doubleval + '}';
    }

}
