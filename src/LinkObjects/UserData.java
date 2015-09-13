/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkObjects;

import java.io.Serializable;

/**
 *
 * @author Mef
 */
public class UserData implements Serializable {
    private String nickname;
    private int apm;
    private float kda;

    public UserData(String nickname, int apm, float kda) {
        this.nickname = nickname;
        this.apm = apm;
        this.kda = kda;
    }   
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getApm() {
        return apm;
    }

    public void setApm(int apm) {
        this.apm = apm;
    }

    public float getKda() {
        return kda;
    }

    public void setKda(float kda) {
        this.kda = kda;
    }

    @Override
    public String toString() {
        return "UserData{" + "nickname=" + nickname + ", apm=" + apm + ", kda=" + kda + '}';
    }        
}
