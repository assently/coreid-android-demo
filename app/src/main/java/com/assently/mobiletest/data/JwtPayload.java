package com.assently.mobiletest.data;

import java.util.Date;

public class JwtPayload {
    private String typ;
    private String dnm;
    private String jti;
    private String hst;
    private String[] aud;
    private String iss;
    private Date iat;
    private Date exp;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getDnm() {
        return dnm;
    }

    public void setDnm(String dnm) {
        this.dnm = dnm;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getHst() {
        return hst;
    }

    public void setHst(String hst) {
        this.hst = hst;
    }

    public String[] getAud() {
        return aud;
    }

    public void setAud(String[] aud) {
        this.aud = aud;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
}
