package com.hukuk.mobileapp.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by hikmet on 8.10.2016.
 */
public class GetMattersResponse implements KvmSerializable {
    public List<Matter> matters ;
    public String statu;
    public String operation;
    public String message;
    public static Class MATTERRESPONSE_CLASS = GetMattersResponse.class;

    public GetMattersResponse(List<Matter> matters, String statu, String operation, String message) {
        this.matters = matters;
        this.statu = statu;
        this.operation = operation;
        this.message = message;
    }

    public GetMattersResponse() {

    }

    public GetMattersResponse(SoapObject _response) {
        this.statu = GetValue(_response, "_x003C_Statu_x003E_k__BackingField");
        this.operation = GetValue(_response, "_x003C_Operation_x003E_k__BackingField");
        this.message = GetValue(_response, "_x003C_Message_x003E_k__BackingField");
    }

    public String GetValue(SoapObject object, String Field) {
        try {
            return object.getProperty(Field).toString();
        } catch (Exception e) {
            return "";
        }
    }
    public  List<Matter> getMatters() {
        return matters;
    }
    public void setMatters(List<Matter> matters) {
        this.matters = matters;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public Object getProperty(int index) {
        Object object = null;
        switch (index) {
            case 0: {
                object = this.matters;
                break;
            }
            case 1: {
                object = this.operation;
                break;
            }
            case 2: {
                object = this.message;
                break;
            }
            case 3: {
                object = this.statu;
                break;
            }
        }
        return object;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.matters = (List<Matter>) obj;
                break;
            }
            case 1: {
                this.operation = obj.toString();
                break;
            }
            case 2: {
                this.message = obj.toString();
                break;
            }
            case 3: {
                this.statu = obj.toString();
                break;
            }
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (index) {
            case 0: {
                propertyInfo.name = "matters";
                propertyInfo.type = PropertyInfo.OBJECT_CLASS;
                break;
            }
            case 1: {
                propertyInfo.name = "operation";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 2: {
                propertyInfo.name = "message";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 3: {
                propertyInfo.name = "statu";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
        }
    }
}
