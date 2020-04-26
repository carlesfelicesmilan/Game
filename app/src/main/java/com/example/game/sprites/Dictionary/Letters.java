package com.example.game.sprites.Dictionary;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Letters {

    Bitmap a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    Bitmap aCap, bCap, cCap, dCap, eCap, fCap, gCap, hCap, iCap, jCap, kCap, lCap, mCap, nCap, oCap, pCap, qCap, rCap, sCap, tCap, uCap, vCap, wCap, xCap, yCap, zCap;
    Bitmap space;

    String text;
    int differenceHeigt;

    private int widthCap, heightCap, width, height, posX, posY;

    HashMap<String, Bitmap> letters = new HashMap<>();

    public Letters(String text, int posX, int posY, Resources resources) {

        this.text = text;
        this.widthCap = (int) resources.getDimension(R.dimen.capital_letter_size);
        this.heightCap = (int) resources.getDimension(R.dimen.capital_letter_size);
        this.width = (int) resources.getDimension(R.dimen.letter_size);
        this.height = (int) resources.getDimension(R.dimen.letter_size);
        this.posX = posX;
        this.posY = posY;

        differenceHeigt = (int) resources.getDimension(R.dimen.capital_letter_size) - (int) resources.getDimension(R.dimen.letter_size);

        a = BitmapFactory.decodeResource(resources, R.drawable.a);
        b = BitmapFactory.decodeResource(resources, R.drawable.b);
        c = BitmapFactory.decodeResource(resources, R.drawable.c);
        d = BitmapFactory.decodeResource(resources, R.drawable.d);
        e = BitmapFactory.decodeResource(resources, R.drawable.e);
        f = BitmapFactory.decodeResource(resources, R.drawable.f);
        g = BitmapFactory.decodeResource(resources, R.drawable.g);
        h = BitmapFactory.decodeResource(resources, R.drawable.h);
        i = BitmapFactory.decodeResource(resources, R.drawable.i);
        j = BitmapFactory.decodeResource(resources, R.drawable.j);
        k = BitmapFactory.decodeResource(resources, R.drawable.k);
        l = BitmapFactory.decodeResource(resources, R.drawable.l);
        m = BitmapFactory.decodeResource(resources, R.drawable.m);
        n = BitmapFactory.decodeResource(resources, R.drawable.n);
        o = BitmapFactory.decodeResource(resources, R.drawable.o);
        p = BitmapFactory.decodeResource(resources, R.drawable.p);
        q = BitmapFactory.decodeResource(resources, R.drawable.q);
        r = BitmapFactory.decodeResource(resources, R.drawable.r);
        s = BitmapFactory.decodeResource(resources, R.drawable.s);
        t = BitmapFactory.decodeResource(resources, R.drawable.t);
        u = BitmapFactory.decodeResource(resources, R.drawable.u);
        v = BitmapFactory.decodeResource(resources, R.drawable.v);
        w = BitmapFactory.decodeResource(resources, R.drawable.w);
        x = BitmapFactory.decodeResource(resources, R.drawable.x);
        y = BitmapFactory.decodeResource(resources, R.drawable.y);
        z = BitmapFactory.decodeResource(resources, R.drawable.z);

        space = BitmapFactory.decodeResource(resources, R.drawable.space);

        aCap = BitmapFactory.decodeResource(resources, R.drawable.acapital);
        bCap = BitmapFactory.decodeResource(resources, R.drawable.bcapital);
        cCap = BitmapFactory.decodeResource(resources, R.drawable.ccapital);
        dCap = BitmapFactory.decodeResource(resources, R.drawable.dcapital);
        eCap = BitmapFactory.decodeResource(resources, R.drawable.ecapital);
        fCap = BitmapFactory.decodeResource(resources, R.drawable.fcapital);
        gCap = BitmapFactory.decodeResource(resources, R.drawable.gcapital);
        hCap = BitmapFactory.decodeResource(resources, R.drawable.hcapital);
        iCap = BitmapFactory.decodeResource(resources, R.drawable.icapital);
        jCap = BitmapFactory.decodeResource(resources, R.drawable.jcapital);
        kCap = BitmapFactory.decodeResource(resources, R.drawable.kcapital);
        lCap = BitmapFactory.decodeResource(resources, R.drawable.lcapital);
        mCap = BitmapFactory.decodeResource(resources, R.drawable.mcapital);
        nCap = BitmapFactory.decodeResource(resources, R.drawable.ncapital);
        oCap = BitmapFactory.decodeResource(resources, R.drawable.ocapital);
        pCap = BitmapFactory.decodeResource(resources, R.drawable.pcapital);
        qCap = BitmapFactory.decodeResource(resources, R.drawable.qcapital);
        rCap = BitmapFactory.decodeResource(resources, R.drawable.rcapital);
        sCap = BitmapFactory.decodeResource(resources, R.drawable.scapital);
        tCap = BitmapFactory.decodeResource(resources, R.drawable.tcapital);
        uCap = BitmapFactory.decodeResource(resources, R.drawable.ucapital);
        vCap = BitmapFactory.decodeResource(resources, R.drawable.vcapital);
        wCap = BitmapFactory.decodeResource(resources, R.drawable.wcapital);
        xCap = BitmapFactory.decodeResource(resources, R.drawable.xcapital);
        yCap = BitmapFactory.decodeResource(resources, R.drawable.ycapital);
        zCap = BitmapFactory.decodeResource(resources, R.drawable.zcapital);

        a = Bitmap.createScaledBitmap(a, width, height, false);
        b = Bitmap.createScaledBitmap(b, width, height, false);
        c = Bitmap.createScaledBitmap(c, width, height, false);
        d = Bitmap.createScaledBitmap(d, width, height, false);
        e = Bitmap.createScaledBitmap(e, width, height, false);
        f = Bitmap.createScaledBitmap(f, width, height, false);
        g = Bitmap.createScaledBitmap(g, width, height, false);
        h = Bitmap.createScaledBitmap(h, width, height, false);
        i = Bitmap.createScaledBitmap(i, width, height, false);
        j = Bitmap.createScaledBitmap(j, width, height, false);
        k = Bitmap.createScaledBitmap(k, width, height, false);
        l = Bitmap.createScaledBitmap(l, width, height, false);
        m = Bitmap.createScaledBitmap(m, width, height, false);
        n = Bitmap.createScaledBitmap(n, width, height, false);
        o = Bitmap.createScaledBitmap(o, width, height, false);
        p = Bitmap.createScaledBitmap(p, width, height, false);
        q = Bitmap.createScaledBitmap(q, width, height, false);
        r = Bitmap.createScaledBitmap(r, width, height, false);
        s = Bitmap.createScaledBitmap(s, width, height, false);
        t = Bitmap.createScaledBitmap(t, width, height, false);
        u = Bitmap.createScaledBitmap(u, width, height, false);
        v = Bitmap.createScaledBitmap(v, width, height, false);
        w = Bitmap.createScaledBitmap(w, width, height, false);
        x = Bitmap.createScaledBitmap(x, width, height, false);
        y = Bitmap.createScaledBitmap(y, width, height, false);
        z = Bitmap.createScaledBitmap(z, width, height, false);

        aCap = Bitmap.createScaledBitmap(aCap, widthCap, heightCap, false);
        bCap = Bitmap.createScaledBitmap(bCap, widthCap, heightCap, false);
        cCap = Bitmap.createScaledBitmap(cCap, widthCap, heightCap, false);
        dCap = Bitmap.createScaledBitmap(dCap, widthCap, heightCap, false);
        eCap = Bitmap.createScaledBitmap(eCap, widthCap, heightCap, false);
        fCap = Bitmap.createScaledBitmap(fCap, widthCap, heightCap, false);
        gCap = Bitmap.createScaledBitmap(gCap, widthCap, heightCap, false);
        hCap = Bitmap.createScaledBitmap(hCap, widthCap, heightCap, false);
        iCap = Bitmap.createScaledBitmap(iCap, widthCap, heightCap, false);
        jCap = Bitmap.createScaledBitmap(jCap, widthCap, heightCap, false);
        kCap = Bitmap.createScaledBitmap(kCap, widthCap, heightCap, false);
        lCap = Bitmap.createScaledBitmap(lCap, widthCap, heightCap, false);
        mCap = Bitmap.createScaledBitmap(mCap, widthCap, heightCap, false);
        nCap = Bitmap.createScaledBitmap(nCap, widthCap, heightCap, false);
        oCap = Bitmap.createScaledBitmap(oCap, widthCap, heightCap, false);
        pCap = Bitmap.createScaledBitmap(pCap, widthCap, heightCap, false);
        qCap = Bitmap.createScaledBitmap(qCap, widthCap, heightCap, false);
        rCap = Bitmap.createScaledBitmap(rCap, widthCap, heightCap, false);
        sCap = Bitmap.createScaledBitmap(sCap, widthCap, heightCap, false);
        tCap = Bitmap.createScaledBitmap(tCap, widthCap, heightCap, false);
        uCap = Bitmap.createScaledBitmap(uCap, widthCap, heightCap, false);
        vCap = Bitmap.createScaledBitmap(vCap, widthCap, heightCap, false);
        wCap = Bitmap.createScaledBitmap(wCap, widthCap, heightCap, false);
        xCap = Bitmap.createScaledBitmap(xCap, widthCap, heightCap, false);
        yCap = Bitmap.createScaledBitmap(yCap, widthCap, heightCap, false);
        zCap = Bitmap.createScaledBitmap(zCap, widthCap, heightCap, false);

        space = Bitmap.createScaledBitmap(space, widthCap/2, heightCap, false);

        letters.put("a", a);
        letters.put("b", b);
        letters.put("c", c);
        letters.put("d", d);
        letters.put("e", e);
        letters.put("f", f);
        letters.put("g", g);
        letters.put("h", h);
        letters.put("i", i);
        letters.put("j", j);
        letters.put("k", k);
        letters.put("l", l);
        letters.put("m", m);
        letters.put("n", n);
        letters.put("o", o);
        letters.put("p", p);
        letters.put("q", q);
        letters.put("r", r);
        letters.put("s", s);
        letters.put("t", t);
        letters.put("u", u);
        letters.put("v", v);
        letters.put("w", w);
        letters.put("x", x);
        letters.put("y", y);
        letters.put("z", z);

        letters.put("A", aCap);
        letters.put("B", bCap);
        letters.put("C", cCap);
        letters.put("D", dCap);
        letters.put("E", eCap);
        letters.put("F", fCap);
        letters.put("G", gCap);
        letters.put("H", hCap);
        letters.put("I", iCap);
        letters.put("J", jCap);
        letters.put("K", kCap);
        letters.put("L", lCap);
        letters.put("M", mCap);
        letters.put("N", nCap);
        letters.put("O", oCap);
        letters.put("P", pCap);
        letters.put("Q", qCap);
        letters.put("R", rCap);
        letters.put("S", sCap);
        letters.put("T", tCap);
        letters.put("U", uCap);
        letters.put("V", vCap);
        letters.put("W", wCap);
        letters.put("X", xCap);
        letters.put("Y", yCap);
        letters.put("Z", zCap);

        letters.put(" ", space);
    }

    public ArrayList<Bitmap> convertToBitmap(String name) {
       ArrayList<Bitmap> text = new ArrayList<>();
        String[] characters = name.split("(?!^)");
        for (int i = 0; i < characters.length; i++) {
            text.add(letters.get(characters[i]));
        }
        return text;
    }

    public void draw(Canvas canvas) {
        ArrayList<Bitmap> name = convertToBitmap(text);
        int x = posX;
        int y = posY;
        for (int i = 0; i < name.size(); i++) {
            if (i == 0) {
                x = posX + getLetterWidth(name.get(0)) * i;
                y = posY;
            }
            else {
                x = x + getLetterWidth(name.get(i-1));
                y = posY + differenceHeigt;
            }
            canvas.drawBitmap(name.get(i), x, y, null);
        }
    }

    public int getLetterWidth(Bitmap letter) {
        return letter.getWidth();
    }

    public int getCapitalLetterWidth() {
        return aCap.getWidth();
    }

    public void updateText(String newText) {
        this.text = newText;
    }

    public void centerText(String text, int x, int panelWidth, int y, int panelHeight) {
        int length = text.length();
        posX = x + panelWidth/2 - length*width/2;
        posY = y + panelHeight/2 - widthCap/2;
    }

}
