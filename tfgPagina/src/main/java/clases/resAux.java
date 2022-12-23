package clases;

public class resAux {
    private String usu;
    private String res;
    private String tit;
    private String aut;

    public resAux(String pUsu, String pRes, String pTit, String pAut) {
        usu = pUsu;
        res = pRes;
        tit = pTit;
        aut = pAut;
    }

    public String getUsu() {
        return usu;
    }

    public String getRes() {
        return res;
    }

    public String getTit() {
        return tit;
    }

    public String getAut() {
        return aut;
    }
}