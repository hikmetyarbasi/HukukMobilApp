package com.hukuk.mobileapp.entity;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by hikmet on 8.10.2016.
 */
public class Matter {
    private int _id;
    private int _musteriid ;
    private String _projekodu;
    private String _projeadi ;
    private int  _projetipiid;
    private String  _notlar;
    private String _kapanmisproje ;
    private String _verigirisdili;
    private String _projebaslangic;
    private String _projebitis;
    private String _devirkaydivar;
    private String _devirtarihi ;
    private double _devirtutari;
    private int _devirtutaribirimiid ;
    private double _devredensure;
    private String _ozeldovizkuruvar;
    private double _ozeldovizkuru1;
    private double _ozeldovizkuru2 ;
    public Matter(SoapObject _response)
    {
        this._id=Integer.parseInt(GetValue(_response, "_x003C_ID_x003E_k__BackingField"));
        this._musteriid=Integer.parseInt(GetValue(_response, "_x003C_MusteriID_x003E_k__BackingField"));
        this._projeadi=GetValue(_response, "_x003C_MusteriID_x003E_k__BackingField");
        this._projekodu=GetValue(_response,"_x003C_ProjeKodu_x003E_k__BackingField");
        this._projebaslangic=GetValue(_response,"_x003C_ProjeBaslangic_x003E_k__BackingField");
        this._projebitis=GetValue(_response,"_x003C_ProjeBitis_x003E_k__BackingField");
    }
    public String GetValue(SoapObject object, String Field)
    {
        try
        {
            return object.getProperty(Field).toString();
        }
        catch (Exception e)
        {
            return "";
        }
    }
    public Matter(int ID,
                  int musteriID,
                  String projeKodu,
                  String projeAdi,
                  int projeTipiID,
                  String notlar,
                  String kapanmisProje,
                  String veriGirisDili,
                  String projeBaslangic,
                  String projeBitis,
                  String devirKaydiVar,
                  String devirTarihi,
                  double devirTutari,
                  int devirTutariBirimiID,
                  double devredenSure,
                  String ozelDovizKuruVar,
                  double ozelDovizKuru1,
                  double ozelDovizKuru2) {
        this._id             = ID;
        this._musteriid           = musteriID;
        this._projekodu           = projeKodu;
        this._projeadi            = projeAdi;
        this._projetipiid         = projeTipiID;
        this._notlar              = notlar;
        this._kapanmisproje       = kapanmisProje;
        this._verigirisdili       = veriGirisDili;
        this._projebaslangic      = projeBaslangic;
        this._projebitis          = projeBitis;
        this._devirkaydivar       = devirKaydiVar;
        this._devirtarihi         = devirTarihi;
        this._devirtutari         = devirTutari;
        this._devirtutaribirimiid = devirTutariBirimiID;
        this._devredensure        = devredenSure;
        this._ozeldovizkuruvar        = ozelDovizKuruVar;
        this._ozeldovizkuru1      = ozelDovizKuru1;
        this._ozeldovizkuru2      = ozelDovizKuru2;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_musteriid() {
        return _musteriid;
    }

    public void set_musteriid(int _musteriid) {
        this._musteriid = _musteriid;
    }

    public String get_projekodu() {
        return _projekodu;
    }

    public void set_projekodu(String _projekodu) {
        this._projekodu = _projekodu;
    }

    public String get_projeadi() {
        return _projeadi;
    }

    public void set_projeadi(String _projeadi) {
        this._projeadi = _projeadi;
    }

    public int get_projetipiid() {
        return _projetipiid;
    }

    public void set_projetipiid(int _projetipiid) {
        this._projetipiid = _projetipiid;
    }

    public String get_notlar() {
        return _notlar;
    }

    public void set_notlar(String _notlar) {
        this._notlar = _notlar;
    }

    public String get_kapanmisproje() {
        return _kapanmisproje;
    }

    public void set_kapanmisproje(String _kapanmisproje) {
        this._kapanmisproje = _kapanmisproje;
    }

    public String get_verigirisdili() {
        return _verigirisdili;
    }

    public void set_verigirisdili(String _verigirisdili) {
        this._verigirisdili = _verigirisdili;
    }

    public String get_projebaslangic() {
        return _projebaslangic;
    }

    public void set_projebaslangic(String _projebaslangic) {
        this._projebaslangic = _projebaslangic;
    }

    public String get_projebitis() {
        return _projebitis;
    }

    public void set_projebitis(String _projebitis) {
        this._projebitis = _projebitis;
    }

    public String get_devirkaydivar() {
        return _devirkaydivar;
    }

    public void set_devirkaydivar(String _devirkaydivar) {
        this._devirkaydivar = _devirkaydivar;
    }

    public String get_devirtarihi() {
        return _devirtarihi;
    }

    public void set_devirtarihi(String _devirtarihi) {
        this._devirtarihi = _devirtarihi;
    }

    public double get_devirtutari() {
        return _devirtutari;
    }

    public void set_devirtutari(double _devirtutari) {
        this._devirtutari = _devirtutari;
    }

    public int get_devirtutaribirimiid() {
        return _devirtutaribirimiid;
    }

    public void set_devirtutaribirimiid(int _devirtutaribirimiid) {
        this._devirtutaribirimiid = _devirtutaribirimiid;
    }

    public double get_devredensure() {
        return _devredensure;
    }

    public void set_devredensure(double _devredensure) {
        this._devredensure = _devredensure;
    }

    public String get_ozeldovizkuruvar() {
        return _ozeldovizkuruvar;
    }

    public void set_ozeldovizkuruvar(String _ozeldovizkuruvar) {
        this._ozeldovizkuruvar = _ozeldovizkuruvar;
    }

    public double get_ozeldovizkuru1() {
        return _ozeldovizkuru1;
    }

    public void set_ozeldovizkuru1(double _ozeldovizkuru1) {
        this._ozeldovizkuru1 = _ozeldovizkuru1;
    }

    public double get_ozeldovizkuru2() {
        return _ozeldovizkuru2;
    }

    public void set_ozeldovizkuru2(double _ozeldovizkuru2) {
        this._ozeldovizkuru2 = _ozeldovizkuru2;
    }
}
