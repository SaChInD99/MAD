package com.example.project;

public class model
{
  String name,date,nic,address,cnumber,url,id;
    model()
    {

    }
    public model(String name, String date, String nic, String address, String cnumber, String url,String id) {
     this.name=name;
     this.date=date;
     this.nic=nic;
     this.date=date;
     this.address=address;
     this.cnumber=cnumber;
     this.url=url;
     this.id=id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnumber() {
        return cnumber;
    }

    public void setCnumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
