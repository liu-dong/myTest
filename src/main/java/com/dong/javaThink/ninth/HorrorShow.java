package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/23
 */
public class HorrorShow {
    static void u(Monster b){
        b.menace();
    }
    static void v(DangerousMonster d){
        d.menace();
        d.destroy();
    }

    static void w(Lethal l){
        l.kill();
    }

    public static void main(String[] args) {
        DangerousMonster barney = new DragonZilla();
        u(barney);
        v(barney);
        Vampire vlad = new VeryBadVampire();
        u(vlad);
        v(vlad);
        w(vlad);

    }
}

/**
 * 怪兽
 */
interface Monster{
    /**
     * 恐吓
     */
    void menace();
}

/**
 * 危险怪兽
 */
interface DangerousMonster extends Monster{

    void destroy();
}

/**
 * 致命
 */
interface Lethal{
    void kill();
}

/**
 * 龙
 */
class DragonZilla implements DangerousMonster{
    @Override
    public void menace() {

    }

    @Override
    public void destroy() {

    }
}

/**
 * 吸血鬼
 */
interface Vampire extends DangerousMonster,Lethal{
    void drinkBlood();
}

class VeryBadVampire implements Vampire {
    @Override
    public void menace() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void kill() {

    }

    @Override
    public void drinkBlood() {

    }
}
