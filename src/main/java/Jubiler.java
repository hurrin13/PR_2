import lombok.SneakyThrows;

import java.util.ArrayList;

public class Jubiler extends Thread implements Miejsce
{
    private Krolestwo k;
    private int blyskotki;

    @Override
    public synchronized void oddajDobra(String nazwa, int ilosc, String kto) throws InterruptedException
    {
        while (!sprobujZabracDobra(nazwa, ilosc))
        {
            wait();
        }
        System.out.println(kto + " zabrał od jubilera " + ilosc + " " + nazwa);
    }

    @Override
    public boolean sprobujZabracDobra(String nazwa, int ilosc)
    {
        if (nazwa.equals("blyskotki"))
        {
            if (blyskotki >= ilosc)
            {
                blyskotki -= ilosc;
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void dodajDobro(String nazwa)
    {
        switch (nazwa)
        {
            case "blyskotki":
                blyskotki++;
                break;
        }
        System.out.println(this);
    }



    @Override
    public void run()
    {
        while (!Thread.interrupted())
        {

            try
            {
                k.getKopalnia().oddajDobra("klejnoty", 5, "Jubiler");
                Thread.sleep(15000);
                blyskotki++;
                System.out.println("Jubiler wytworzył błyskotke");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }

    public Jubiler(Krolestwo k, int blyskotki)
    {
        this.k = k;
        this.blyskotki = blyskotki;
    }

    @Override
    public String toString()
    {
        return "Stan jubilera: Blyskotki: " + blyskotki;
    }
}