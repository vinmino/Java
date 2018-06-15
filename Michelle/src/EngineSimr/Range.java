package EngineSimr;/*
                      RangeGames
   
     Program to test students on aircraft range and performance
                a)   dry turbojet
                b)   afterburning turbojet
                c)   turbofan with separate nozzle
                d)   ramjet

                     Version 1.3.e   - 15 Jul 03

                         Written by Tom Benson
                       NASA Glenn Research Center

>                              NOTICE
>This software is in the Public Domain.  It may be freely copied and used in
>non-commercial products, assuming proper credit to the author is given.  IT
>MAY NOT BE RESOLD.  If you want to use the software for commercial
>products, contact the author.
>No copyright is claimed in the United States under Title 17, U. S. Code.
>This software is provided "as is" without any warranty of any kind, either
>express, implied, or statutory, including, but not limited to, any warranty
>that the software will conform to specifications, any implied warranties of
>merchantability, fitness for a particular purpose, and freedom from
>infringement, and any warranty that the documentation will conform to the
>program, or any warranty that the software will be error free.
>In no event shall NASA be liable for any damages, including, but not
>limited to direct, indirect, special or consequential damages, arising out
>of, resulting from, or in any way connected with this software, whether or
>not based on warranty, contract, tort or otherwise, whether or not injury
>was sustained by persons or property or otherwise, and whether or not loss
>was sustained from, or arose out of the results of, or use of, the software
>or services provided hereunder.
 
      New Test  -  * re-organize classes
                   * add a ramjet
                   * add some initial stills
                     add turboprop
                   * add card layout
                   * take out the "data" option of input... only word problems
                   * add the range games
                   *   do the layout
                   *   add the functionality
                   *   send printed to an array to be read by another applet
                   * fix-up multiple choice numbers
                     move images into main directory .. problem in .zip
      Old Test  -
 
                                                     TJB 15 Jul 03
*/

import java.awt.*;
import java.lang.Math ;

public class Range extends java.applet.Applet {

   final double convdr = 3.14515926/180.;
 
   int abflag,entype,lunits,inflag,pt2flag ;
   int plkeep,enkeep,inkeep,abkeep,untkeep,pltkeep ;
   int numeng,gamopt,arsched,plttyp,showcom ;
               // Flow variables
   static double g0d,g0,rgas,gama,cpair ;
   static double tt4,tt4d,tt7,tt7d,p3p2d,p3fp2d,byprat,throtl;
   static double fsmach,altd,alt,ts0,ps0,q0,u0d,u0,a0,rho0,tsout,psout;
   static double epr,etr,npr,snpr,fnet,fgros,dram,sfc,fa,eair,uexit,ues;
   static double fnd,fnlb,fglb,drlb,flflo,fuelrat,fntot,eteng;
   static double a8,a8rat,a8d,afan,a7,m2;
   static double ac,a2,a2d,acore,a4,a4p,fhv,mfr ;
   static double altmin,altmax,u0min,u0max,thrmin,thrmax;
   static double etmin,etmax,cprmin,cprmax,t4min,t4max;
   static double a2min,a2max,a8min,a8max,t7min,t7max;
   static double bypmin,bypmax,fprmin,fprmax;
   static double vmn1,vmn2,vmn3,vmx1,vmx2,vmx3 ;
   static double lconv1,lconv2,fconv,pconv,tconv,tref,mconv1,mconv2,econv ;
   static double aconv,bconv,cconv ;
                        // range variables
   static double fueload,range,rgoal,drag;
   static double ru0,rdrag,rthrotl,rcd,rrho0,rtime ;
   static double aweight,aspecr,eff,winga,cd0,rngmin ;
   static double timim,velim,disim,mass,accel ;
   static double time,rnge,eweight,cl,cd,tmind,tovel,todis,totim ;
   static double timg,rngg,masg,aclg,velg,disg;
   int playflg,nrong,nrite,prmode,prtflg,probflg,fortyp ;
   int rngcal,curcal,warnflag,trys,trymax,rngopt,planetyp ;
   static int[] ansl = new int[4] ;
   static int[] mapn = new int[4] ;
   static int[] chosn = new int[4] ;
               // Station Variables
   static double[] trat = new double[20] ;
   static double[] tt   = new double[20] ;
   static double[] prat = new double[20] ;
   static double[] pt   = new double[20] ;
   static double[] eta  = new double[20] ;
   static double[] gam  = new double[20] ;
   static double[] cp   = new double[20] ;
   static double[] s    = new double[20] ;
   static double[] v    = new double[20] ;

   int counter,antrig ;
   int ordkeep,abskeep ;
   static double begx,endx,begy,endy ;
   static double[] pltx = new double[26] ;
   static double[] plty = new double[26] ;
   static String labx,laby ;

   Solver solve ;
   Viewer view ;
   Mdpnl mdpnl ;
   L l ;
   CardLayout probin,layout ;
   Printo printo ;
   Image offscreenImg ;
   Graphics offsGg ;
   Image offImg2 ;
   Graphics off2Gg ;
   Image[] aneximg = new Image[4] ;
   Image[] exrimg  = new Image[9] ;
   Image[] exdimg  = new Image[9] ;
   Image[] anfiimg = new Image[4] ;
   Image[] firimg  = new Image[9] ;
   Image[] fidimg  = new Image[9] ;
   Image[] anlnimg = new Image[4] ;
   Image[] lnrimg  = new Image[9] ;
   Image[] lndimg  = new Image[9] ;
   Image[] anhyimg = new Image[4] ;
   Image[] hyrimg  = new Image[9] ;
   Image[] hydimg  = new Image[9] ;
   Image execimg ;
   Image fiteimg ;
   Image linrimg ;
   Image hyprimg ;

   public void init() {
     int i;
     Range a = new Range() ;
     solve = new Solver() ;

     for (i = 1; i <= 4; i++ ) {
       aneximg[i-1] = getImage(getCodeBase(),
               "ex" + i + ".gif") ;
       anfiimg[i-1] = getImage(getCodeBase(),
               "fi" + i + ".gif") ;
       anlnimg[i-1] = getImage(getCodeBase(),
               "ln" + i + ".gif") ;
       anhyimg[i-1] = getImage(getCodeBase(),
               "hy" + i + ".gif") ;
     }

     for (i = 1; i <= 9; i++ ) {
       firimg[i-1] = getImage(getCodeBase(),
               "fir" + i + ".gif") ;
       fidimg[i-1] = getImage(getCodeBase(),
               "fid" + i + ".gif") ;
       exrimg[i-1] = getImage(getCodeBase(),
               "exr" + i + ".gif") ;
       exdimg[i-1] = getImage(getCodeBase(),
               "exd" + i + ".gif") ;
       lnrimg[i-1] = getImage(getCodeBase(),
               "lnr" + i + ".gif") ;
       lndimg[i-1] = getImage(getCodeBase(),
               "lnd" + i + ".gif") ;
       hyrimg[i-1] = getImage(getCodeBase(),
               "hyr" + i + ".gif") ;
       hydimg[i-1] = getImage(getCodeBase(),
               "hyd" + i + ".gif") ;
     }

     execimg = getImage(getCodeBase(),"exec.gif") ;
     fiteimg = getImage(getCodeBase(),"fite.gif") ;
     linrimg = getImage(getCodeBase(),"linr.gif") ;
     hyprimg = getImage(getCodeBase(),"hypr.gif") ;

     offscreenImg = createImage(this.size().width,
                      this.size().height) ;
     offsGg = offscreenImg.getGraphics() ;
     offImg2 = createImage(this.size().width,
                      this.size().height) ;
     off2Gg = offImg2.getGraphics() ;

     setLayout(new GridLayout(4,1,0,0)) ;

     solve.setDefaults () ;
 
     view   = new Viewer(this) ;
     mdpnl = new Mdpnl(this) ;
     l = new L(this) ;
     printo = new Printo() ;

     add(view) ;
     add(mdpnl) ;
     add(l) ;
     add(printo) ;

     view.start() ;
     solve.getFreeStream ();
     solve.getThermo () ;
     solve.getGeo() ;
     solve.getPerform () ;
     inflag = 1 ;
     solve.getFreeStream ();
     solve.getThermo () ;
     solve.getGeo() ;
     solve.getPerform () ;
     computeRange () ;
  }
 
  public Insets insets() {
     return new Insets(0,10,5,0) ;
  }

  public void computeRange() { 
     if (curcal == 1) {
        setRange() ;
        showProb() ;
        return ;
     }

     if (rngcal > 0) {    /*  grade the problem  */
        rangeOut () ;
        return ;
     }

  }

  public void setRange() {
     int iter;

     if (planetyp == 0) {        /* modeled on citation */
       numeng = 2 ;
       altd = lconv1 * (10000. + 25000 * Math.random()) ;
       fueload = 2000. + Math.random() * 3000. ;
       ru0 = u0d = lconv2 * (300. + 325. * Math.random()) ;
       aspecr = 7.0 ; winga = 322.; eff = .8 ; eweight=7500.;
     }
     if (planetyp == 1) {        /* modeled on f-15 */
       abflag = 1 ;
       numeng = 2 ;
       altd = lconv1 * (10000. + 40000 * Math.random()) ;
       fueload = 5000. + Math.random() * 10000. ;
       ru0 = u0d = lconv2 *(300. + 950. * Math.random()) ;
       aspecr = 3.0 ; winga = 600.; eff = .7 ; eweight=31000. ;
     }
     if (planetyp == 2) {        /* modeled on 747   */
       numeng = 4 ;
       altd = lconv1 * (10000. + 30000 * Math.random()) ;
       fueload = 35000. + Math.random() * 65000. ;
       ru0 = u0d = lconv2 * (300. + 325. * Math.random()) ;
       aspecr = 7.0 ; winga = 5500.; eff = .9 ; eweight=400000.;
     }
     if (planetyp == 3) {        /* modeled on Hyper-X    */
       numeng = 1 ;
       altd = lconv1 * (20000. + 30000 * Math.random()) ;
       fueload = 1500. + Math.random() * 2000. ;
       ru0 = u0d = lconv2 * (500. + 4000. * Math.random()) ;
       aspecr = 1.0 ; winga = 60.; eff = .5 ; eweight=3500.;
     }
     entype = planetyp ;
     aweight = eweight + fueload ;
     throtl = rthrotl = 70. + 30. * Math.random() ;
     mass = aweight / g0 ;
     mdpnl.mdleft.o1.setText(String.valueOf(filter0(fconv*aweight))) ;
                              /* determine cd0 */
     solve.getFreeStream ();
     solve.getThermo () ;
     a8 = a8d * Math.sqrt(trat[7]) / prat[7] ;
     solve.getPerform () ;
     if (fntot <= 0.0) setRange() ;
     rdrag = drag = fntot ;
     rtime = time = fueload / fuelrat ;
     mdpnl.mdright.repaint() ;
     cl = aweight / (q0 * winga );
     rcd = cd = drag / (q0 * winga ) ;
     cd0  = cd - cl*cl/(3.14159 * aspecr * eff) ;
     if (cd0 < 0.0) cd0 = 0.0 ;
     rrho0 = rho0 ;
                                   /* determine target range */
     throtl = 100.0 ;                       /* minimum range */
     solve.getThermo () ;
     a8 = a8d * Math.sqrt(trat[7]) / prat[7] ;
     solve.getPerform () ;
     tmind = fntot - drag ;
     iter = 0 ;
     while(Math.abs(tmind) >= 5.0 && iter < 25 ) {
       ++ iter ;
       u0d = (1.0+tmind/aweight)*u0d ;
       solve.getFreeStream ();
       solve.getThermo () ;
       a8 = a8d * Math.sqrt(trat[7]) / prat[7] ;
       solve.getPerform () ;
       cl = aweight / (q0 * winga );
       cd = cd0 + cl*cl/(3.14159 * aspecr * eff) ;
       drag = cd * q0 * winga ;
       tmind = fntot - drag ;
     }
     time = fueload / fuelrat ;
     rngmin = u0 *3600./5280. * time ;
     if (rngopt >= 4) {
       rgoal = 2.0 * rngmin ;
     }
                                        /* reset problem conditions */
     throtl = rthrotl ;
     u0d = ru0 ;
     time = rtime ;

     return ;
  }
 
  public void rangeOut () {          /* output  range information  */
     int correct,index,pic,pcor,numb;

     correct = 0 ;

     if (prtflg == 1) {
        printProb() ;
        if(rngcal == 1 ) {   /* rate problem -find range */
           printo.prnt.appendText( "\n Velocity " + filter0(u0d) + 
           "   Time " + filter3(time) ) ;
        }
        if(rngcal == 2) {           /* rate problems - find time */
           printo.prnt.appendText("\n Fuel Load  " + filter0(mconv1*fueload) +
           "  Fuel Flow " + filter0(mconv1*fuelrat)) ;
        }
        if(rngcal == 3) {            /* rate problem -find range */
           printo.prnt.appendText("\n Fuel Load " + filter0(mconv1*fueload) +
            "   Fuel Flow " + filter0(mconv1*fuelrat) + 
            "  Velocity " + filter0(u0d)) ;
        }
        if(rngcal == 11) {           /* force problem - find mass */
           printo.prnt.appendText( "\n Weight  " + filter0(fconv*aweight));
        }
        if(rngcal == 12) {       /* force problem - find acceleration */
          printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "  Thrust " + filter0(fconv*fntot) ) ;
        }
        if(rngcal == 13) {   /* force problem - find velocity given time */
          printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "    Thrust " + filter0(fconv*fntot) +
          "    Accel  " + filter3(lconv1*accel) +
          "    Time  " + filter3(timim)) ;
        }
        if(rngcal == 14) {   /* force problem - find time given velocity */
          printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "   Thrust " + filter0(fconv*fntot) +
          "   Accel  " + filter3(lconv1*accel) +
          "  Velocity" + filter0(lconv1*velim));
        }
        if(rngcal == 15) {  /* force problem - find distance given time */
          printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "   Thrust " + filter0(fconv*fntot) +
          "   Accel  " + filter3(lconv1*accel) +
          "    Time  " + filter3(timim)) ;
        }
        if(rngcal == 16) {   /* force problem - find time given distance */
          printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "   Thrust " + filter0(fconv*fntot) +
          "   Accel  " + filter3(lconv1*accel) +
          "  Distance " + filter0(lconv1*disim));
        }
        if(rngcal == 17) {   /* force problem - find velocity given distance */
           printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "   Thrust " + filter0(fconv*fntot) +
          "   Accel  " + filter3(lconv1*accel) +
          "  Distance " + filter0(lconv1*disim));
        }
        if(rngcal == 18) {  /* force problem - find distance given velocity */
           printo.prnt.appendText("\n Weight " + filter0(fconv*aweight) +
          "   Thrust " + filter0(fconv*fntot) +
          "   Accel  " + filter3(lconv1*accel) +
          "  Velocity" + filter0(lconv1*velim));
        }

     }

     if (prmode == 1) {
        if(rngcal == 1 ) {         /* rate problem -find range */
         if (rngg <= 1.05*rnge && rngg >= .95*rnge) correct =1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter0(lconv2*rngg) +
            "\n Correct Answer - " + filter0(lconv2*rnge)) ;
        }
        if(rngcal == 2) {           /* rate problems - find time */
         if (timg <= 1.1*time && timg >= .9*time) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter3(timg) +
            "\n Correct Answer - " + filter3(time)) ;
        }
        if(rngcal == 3) {            /* rate problem -find range */
         if (rngg <= 1.05*rnge && rngg >= .95*rnge) correct =1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter0(lconv2*rngg) +
            "\n Correct Answer - " + filter0(lconv2*rnge)) ;
        }
        if(rngcal == 11) {           /* force problem - find mass */
         if (masg <= 1.1*mass && masg >= .9*mass) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter0(lconv1*masg) +
            "\n Correct Answer - " + filter0(lconv1*mass)) ;
        }
        if(rngcal == 12) {           /* force problem - find acceleration */
         if (aclg <= 1.1*accel && aclg >= .9*accel) correct =1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter3(lconv1*aclg) +
            "\n Correct Answer - " + filter3(lconv1*accel)) ;
        }
        if(rngcal == 13) {        /* force problem - find velocity given time */
         if (velg <= 1.1*tovel && velg >= .9*tovel) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter0(lconv1*velg) +
            "\n Correct Answer - " + filter0(lconv1*tovel)) ;
        }
        if(rngcal == 14) {        /* force problem - find time given velocity */
         if (timg <= 1.1*totim && timg >= .9*totim) correct =1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter3(timg) +
            "\n Correct Answer - " + filter3(totim)) ;
        }
        if(rngcal == 15) {        /* force problem - find distance given time */
         if (disg <= 1.1*todis && disg >= .9*todis) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
            "\n Submitted Answer - " + filter0(lconv1*disg) +
            "\n Correct Answer - " + filter0(lconv1*todis)) ;
        }
        if(rngcal == 16) {        /* force problem - find time given distance */
         if (timg <= 1.1*totim && timg >= .9*totim) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
             "\n Submitted Answer - " + filter3(timg) + 
             "\n Correct Answer - " + filter3(totim)) ;
        }
        if(rngcal == 17) {   /* force problem - find velocity given distance */
         if (velg <= 1.1*tovel && velg >= .9*tovel) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
             "\n Submitted Answer - " + filter0(lconv1*velg) + 
             "\n Correct Answer - " + filter0(lconv1*tovel)) ;
        }
        if(rngcal == 18) {   /* force problem - find distance given velocity */
         if (disg <= 1.1*todis && disg >= .9*todis) correct = 1 ;
         if (prtflg == 1) printo.prnt.appendText(
             "\n Submitted Answer - " + filter0(lconv1*disg) + 
             "\n Correct Answer - " + filter0(lconv1*todis)) ;
        }
     }

     if (prmode == 0) {                  /*  multiple  choice  answers */
                                      /*  which button was picked  */
        pic = 0 ;
        if (l.lr.up.anmc.ch1.getState()) {
              pic = 0  ;
              if (chosn[0] == 1) return ;
              chosn[0] = 1 ;
              if (prtflg == 1) printo.prnt.appendText(
                 "\n Submitted Answer - " + l.lr.up.anmc.lch1.getText()); 
        }
        if (l.lr.up.anmc.ch2.getState()) {
              pic = 1  ;
              if (chosn[1] == 1) return ;
              chosn[1] = 1 ;
              if (prtflg == 1) printo.prnt.appendText(
                 "\n Submitted Answer - " + l.lr.up.anmc.lch2.getText()); 
        }
        if (l.lr.up.anmc.ch3.getState()) {
              pic = 2  ;
              if (chosn[2] == 1) return ;
              chosn[2] = 1 ;
              if (prtflg == 1) printo.prnt.appendText(
                 "\n Submitted Answer - " + l.lr.up.anmc.lch3.getText()); 
        }
        if (l.lr.up.anmc.ch4.getState()) {
              pic = 3  ;
              if (chosn[3] == 1) return ;
              chosn[3] = 1 ;
              if (prtflg == 1) printo.prnt.appendText(
                 "\n Submitted Answer - " + l.lr.up.anmc.lch4.getText()); 
        }

        if (mapn[pic] == 0) {
           correct = 1 ;
           if( pic == 0) {
              l.lr.up.anmc.sco1.setText("---->");
              l.lr.up.anmc.sco1.setForeground(Color.blue) ;
           }
           if( pic == 1) {
              l.lr.up.anmc.sco2.setText("---->");
              l.lr.up.anmc.sco2.setForeground(Color.blue) ;
           }
           if( pic == 2) {
              l.lr.up.anmc.sco3.setText("---->");
              l.lr.up.anmc.sco3.setForeground(Color.blue) ;
           }
           if( pic == 3) {
              l.lr.up.anmc.sco4.setText("---->");
              l.lr.up.anmc.sco4.setForeground(Color.blue) ;
           }
           if (prtflg == 1) printo.prnt.appendText(
              "\n  Answer is Correct ....  Well Done !"); 
        }
        else {
           if( pic == 0) {
              l.lr.up.anmc.sco1.setText(" X ");
              l.lr.up.anmc.sco1.setForeground(Color.red) ;
           }
           if( pic == 1) {
              l.lr.up.anmc.sco2.setText(" X ");
              l.lr.up.anmc.sco2.setForeground(Color.red) ;
           }
           if( pic == 2) {
              l.lr.up.anmc.sco3.setText(" X ");
              l.lr.up.anmc.sco3.setForeground(Color.red) ;
           }
           if( pic == 3) {
              l.lr.up.anmc.sco4.setText(" X ");
              l.lr.up.anmc.sco4.setForeground(Color.red) ;
           }

           l.lr.dwn.d2.stat.setText("Try Again") ;
           if (prtflg == 1) {
              if (mapn[pic] == 1) printo.prnt.appendText(
                 "\n Incorrect Answer ....  Probable Math Error");
              if (mapn[pic] == 2) printo.prnt.appendText(
                 "\n Incorrect Answer ....  Probable Input Error");
              if (mapn[pic] == 3) printo.prnt.appendText(
                 "\n Incorrect Answer ....  Can't Determine Error");
           }
        }
     }

     if (correct == 1) {
        ++ nrite;
        antrig = 2 ;
        counter = 0 ;
     }
     if (correct == 0) {
        ++ nrong;
        antrig = 1 ;
        counter = 0 ;
     }

     -- trys ;
     if (trys == 0) {
        rngcal = 0 ;
        l.lr.dwn.d2.stat.setText("No More Tries") ;
     }
     else {
         l.lr.dwn.d3.remain.setText(String.valueOf(trys)) ;
     }

     if (playflg != 3) {
        if (correct == 1) {
           l.lr.dwn.d2.stat.setText("WELL DONE ! -   Try Another Problem") ;
           l.lr.dwn.d3.rite.setText(String.valueOf(nrite)) ;
        }
        if (correct == 0) {
           if(rngcal <= 3)  l.lr.dwn.d2.stat.setText(
              "Sorry ! Try Again- HINT -> Rate X Time = Amount");
           if(rngcal == 11) l.lr.dwn.d2.stat.setText(
               "Sorry! Try Again-HINT-> mass X gravity = weight");
           if(rngcal == 12) l.lr.dwn.d2.stat.setText(
               "Sorry! Try Again-HINT -> mass X accel = force") ;
           if(rngcal == 13 || rngcal == 14) l.lr.dwn.d2.stat.setText(
               "Sorry! Try Again-HINT -> accel X time = vel") ;
           if(rngcal >= 15) l.lr.dwn.d2.stat.setText(
               "Sorry! Try Again-HINT -> .5 accel X time square= dist");
           l.lr.dwn.d3.rong.setText(String.valueOf(nrong)) ;
        }
     }
     else {
           l.lr.dwn.d2.stat.setText("Go to the next Problem") ;
     }
 
     if (prtflg == 1) {
        numb = trymax - trys ;
        if (correct == 1) printo.prnt.appendText("\n CORRECT  on  Try " + numb);
        if (correct == 0) printo.prnt.appendText("\n Wrong  on  Try " + numb);
        printo.prnt.appendText("  Totals  - Right " + nrite + 
          "  Wrong  " + nrong);
     }

     rngcal = 0 ;
     return ;
  }

  public void printProb () {  /* save problem information */
                                          /* problem type */
    if(rngopt == 1) printo.prnt.appendText("\n \n Rate Problem - find range given time ");
    if(rngopt == 2) printo.prnt.appendText("\n \n Rate Problem - find time given fuel ");
    if(rngopt == 3) printo.prnt.appendText("\n \n Rate Problem - find range given fuel ");
    if(rngopt == 11)printo.prnt.appendText("\n \n Weght Problem - find mass ");
    if(rngopt == 12)printo.prnt.appendText("\n \n Motion Problem - find accel ");
    if(rngopt == 13)printo.prnt.appendText("\n \n Motion Problem - find velocity given time ");  
    if(rngopt == 14)printo.prnt.appendText("\n \n Motion Problem - find time given velocity ");  
    if(rngopt == 15)printo.prnt.appendText("\n \n Motion Problem - find distance given time ");  
    if(rngopt == 16)printo.prnt.appendText("\n \n Motion Problem - find time given distance ");  
    if(rngopt == 17)printo.prnt.appendText("\n \n Motion Problem - find vel given dist ");
    if(rngopt == 18)printo.prnt.appendText("\n \n Motion Problem - find dist given vel ");

    if (prmode == 1) printo.prnt.appendText("     Type-In  Answer ");
    if (prmode == 0) printo.prnt.appendText("     Multiple Choice ");
    if (lunits == 0) printo.prnt.appendText(" English Units ");
    if (lunits == 1) printo.prnt.appendText(" Metric Units ");
    return;
  }


  public void showProb() { 
    double u0fs ;
    String str1 ;
 
     str1 = "Problems" ;

     if(rngopt <= 3 ) {
        solve.getFreeStream() ;
        solve.getThermo() ;
        a8 = a8d * Math.sqrt(trat[7]) /prat[7] ;
        solve.getPerform() ;
        time = fueload / fuelrat ;
        rnge = u0 *3600./5280. * time ;
        mdpnl.mdright.repaint() ;
        if (prmode == 0) loadMchoice() ;
     }

     if(rngopt >= 10 ) {
       altd = 0.0 ;
       u0d  = 0.0 ;
       u0fs = 0.0 ;
       if (planetyp == 3) {
          altd = lconv1 *30000. ;
          u0d = lconv2* 2000. ;
          u0fs = 2000. * 5280./3600. ;
       }
       solve.getFreeStream ();
       solve.getThermo () ;
       solve.getPerform () ;
       mdpnl.mdright.repaint() ;
       accel = fntot/mass ;
       if (rngopt == 13 || rngopt == 15) {
          timim = 10.0 + 20.0 * Math.random() ;
          tovel = accel * timim + u0fs ;
          todis = accel * timim * timim / 2.0  + u0fs*timim ;
       }
       if (rngopt == 14 || rngopt == 18)  {
          velim = 100.0 + 300.0 * Math.random() ;
          if (planetyp == 3) {
              velim = u0fs + 1000.0 * Math.random() ;
          }
          totim = (velim - u0fs) / accel ;
          todis = accel * totim * totim / 2.0  + u0fs * totim ;
       }
       if (rngopt == 16 || rngopt == 17)  {
          disim = 1000.0 + 4000.0 * Math.random() ;
          totim = Math.sqrt(u0fs*u0fs/accel/accel+2.0*disim/accel)-u0fs/accel;
          tovel = accel * totim + u0fs ;
       }
       if (prmode == 0) loadMchoice () ;
     }

     mdpnl.mdleft.o1.setText(String.valueOf(filter0(fconv*aweight))) ;
     mdpnl.mdleft.o2.setText(String.valueOf(filter3(fsmach))) ;

     if (rngopt == 1) {
        str1 = "\nHow far can this airplane fly \n" + "in " + filter3(time) + " hours?";
     }
     if (rngopt == 2){
        if (lunits == 0) str1 = "\nHow many hours can this airplane fly if it is\ncarrying" + filter0(mconv1*fueload) + " pounds of  fuel?";
        if (lunits == 1) str1 = "\nHow many hours can this airplane fly if it is\ncarrying" + filter0(mconv1*fueload) + " kilograms of  fuel?";
     }
     if (rngopt == 3) {
        if (lunits == 0) str1 = "\nHow far can this airplane fly if it is \ncarrying" + filter0(mconv1*fueload) + " pounds of  fuel?";
        if (lunits == 1) str1 = "\nHow far can this airplane fly if it is \ncarrying" + filter0(mconv1*fueload) + " kilograms of  fuel?";
     }
     if (rngopt == 11)  {
        str1 = "\nWhat is the mass of this airplane?";
     }
     if (rngopt == 12)  {
        if (fortyp == 0)  {
          if (lunits == 0) str1 = "\nIf the mass of this airplane is \n" + filter0(mconv2*mass) + " slugs, \nhow fast can it accelerate?";
          if (lunits == 1) str1 = "\nIf the mass of this airplane is \n" + filter0(mconv2*mass) + " kilograms, \nhow fast can it accelerate?";
        }
        else str1 = "\nHow fast does this airplane accelerate?" ;
     }
     if (rngopt == 13) {
        if (fortyp == 0)  {
            if (lunits==0) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " feet \nper second squared.\n What is the speed after " + filter3(timim) + " seconds?";
            if (lunits==1) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " meters \nper second squared.  \nWhat is the speed after " + filter3(timim) + " seconds?" ;
         }
         else {
            str1 = "\nAs this airplane accelerates, \nwhat is the speed after " + filter3(timim) + " seconds?" ;
         }
     }
     if (rngopt == 14)  {
        if (fortyp == 0)  {
            if (lunits==0) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " feet \nper second squared. \nWhen does it reach " + filter0(lconv1*velim) + " feet per second?";
            if (lunits==1) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + "  meters \nper second squared. \nWhen does it reach " + filter0(lconv1*velim) + " meters per second?" ;
         }
         else {
            if (lunits == 0) str1 = "\nHow long does it take for this aircraft to reach \n" + filter0(lconv1*velim) + " feet per second?" ;
            if (lunits == 1) str1 = "\nHow long does it take for this aircraft to reach \n" + filter0(lconv1*velim) + " meters per second?" ;
         }
     }
     if (rngopt == 15) {
        if (fortyp == 0)  {
            if (lunits==0) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " feet \nper second squared. \nWhere is it after " + filter3(timim) + " seconds?";
            if (lunits==1) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " meters \nper second squared. \nWhere is it after " + filter3(timim) +  " seconds?" ;
         }
         else {
            str1 = "\nWhere is this aircraft after \n" + filter3(timim) + " seconds?";
         }
     }
     if (rngopt == 16) {
        if (fortyp == 0)  {
            if (lunits==0) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " feet \nper second squared. \nHow long does it take to move " + filter0(lconv1*disim) + " feet?";
            if (lunits==1) str1 = "\nThis airplane accelerates at " + filter3(lconv1*accel) + " meters \nper second squared. \nHow lng does it take to move " + filter0(lconv1*disim) + "  meters?" ;
        }
        else {
            if (lunits == 0) str1 = "\nHow long does it take for this aircraft to move \n" + filter0(lconv1*disim) + " feet?" ;
            if (lunits == 1) str1 = "\nHow long does it take for this aircraft to move \n" + filter0(lconv1*disim) + "  meters?" ;
        }
     }
     if (rngopt == 17)  {
        if (lunits == 0) str1 = "\nHow fast is this aircraft going in \n" + filter0(lconv1*disim) + " feet?";
        if (lunits == 1) str1 = "\nHow fast is this aircraft going in \n" + filter0(lconv1*disim) + "  meters?";
     }
     if (rngopt == 18)  {
        if (lunits == 0) str1 = "\nAt what point does this aircraft \nreach" + filter0(lconv1*velim) + " feet per second?";
        if (lunits == 1) str1 = "\nAt what point does this aircraft \nreach" + filter0(lconv1*velim) + " meters per second?";
     }

     l.ll.prout.setText(str1) ;

     if (planetyp == 3) {
         str1 = "\n Hypersonic problems are VERY difficult \nWatch your units!" ;
         l.ll.prout.append(str1) ;
     }
     curcal = 0 ;
     return;
  }
 
  public void loadMchoice ()  {     /* load multiple choice boxes */
     double[] ans  = new double[4] ;
     int index ;

     for (index = 0; index <=3 ; ++ index) {
          ansl[index] = index ;
     }

     if(rngopt == 1) {         /* rate problem - find range given time */
         ans[0] = lconv2*rnge ;     ans[1] = lconv2 * u0 *3600./5280. / time ;
         ans[2] = mconv1 * fuelrat * time ;
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 2) {         /* rate problem - find time given fuel */
         ans[0] = time ;                  ans[1] = fuelrat/fueload ;
         ans[2] = mconv1*fueload /(lconv2*u0*3600./5280.) ;
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 3) {         /* rate problem - find range given fuel */
         ans[0] = lconv2*rnge ;     ans[1] = lconv2*u0 *3600./5280. / time ;
         ans[2] = fnlb/flflo*(lconv2*u0 *3600./5280.); 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 11) {        /* force problem - find mass */
         ans[0] = mconv2*mass ;         ans[1] =  fconv*aweight   ;
         ans[2] = mconv1*fuelrat / g0d; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 12) {        /* force problem - find acceleration */
         ans[0] = lconv1*accel ;      
         ans[1] = aweight/fntot*g0d ; ans[2] = mconv1*fuelrat/(fconv*aweight)*g0d ;
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 13) {        /* force problem - find velocity given time */
         ans[0] = lconv1*tovel ;          ans[1] = lconv1*accel / timim;
         ans[2] = fconv*aweight / timim ; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 14) {        /* force problem - find time given velocity */
         ans[0] = totim ;            ans[1] =  lconv1*accel * lconv1*velim;
         ans[2] = fconv*aweight/(lconv1*velim) ; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 15) {        /* force problem - find distance given time */
         ans[0] = lconv1*todis ;            ans[1] =  .5 * lconv1*accel * timim;
         ans[2] = lconv1*accel*timim*timim ;
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 16) {        /* force problem - find time given distance */
         ans[0] = totim ;         ans[1] =  Math.sqrt(.5 *disim/accel);
         ans[2] = disim/accel   ; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 17) {       /* force problem - find velocity given distance */
         ans[0] = lconv1*tovel ;          ans[1] =  lconv1 * accel / totim;
         ans[2] = fconv * aweight/totim ; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
     if(rngopt == 18) {      /* force problem - find distance given velocity */
         ans[0] = lconv1*todis ;            ans[1] =  .5 * lconv1*accel * totim;
         ans[2] = lconv1*accel*totim*totim; 
         ans[3] = (.8 + .4 * Math.random()) * ans[0] ;
     }
                                        /* load the boxes randomly */
     index = (int) (4 * Math.random());
     if (Math.abs(ans[index]) > 100.0) {
         l.lr.up.anmc.lch1.setText(String.valueOf(filter0(ans[index])));
     }
     else {
         l.lr.up.anmc.lch1.setText(String.valueOf(filter3(ans[index])));
     }
     mapn[0] = ansl[index] ;
     if (index != 3) {
          ans[index]  = ans[3] ;
          ansl[index] = ansl[3] ;
     }

     index = (int) (3 * Math.random()) ;
     if (Math.abs(ans[index]) > 100.0) {
         l.lr.up.anmc.lch2.setText(String.valueOf(filter0(ans[index])));
     }
     else {
         l.lr.up.anmc.lch2.setText(String.valueOf(filter3(ans[index])));
     }
     mapn[1] = ansl[index] ;
     if (index != 2) {
          ans[index]  = ans[2] ;
          ansl[index] = ansl[2] ;
     }

     index = (int) (2 * Math.random()) ;
     if (Math.abs(ans[index]) > 100.0) {
         l.lr.up.anmc.lch3.setText(String.valueOf(filter0(ans[index])));
     }
     else {
         l.lr.up.anmc.lch3.setText(String.valueOf(filter3(ans[index])));
     }
     mapn[2] = ansl[index] ;
     if (index != 1) {
          ans[index]  = ans[1] ;
          ansl[index] = ansl[1] ;
     }

     if (Math.abs(ans[0]) > 100.0) {
         l.lr.up.anmc.lch4.setText(String.valueOf(filter0(ans[0])));
     }
     else {
         l.lr.up.anmc.lch4.setText(String.valueOf(filter3(ans[0])));
     }
     mapn[3] = ansl[0] ;
 
     for (index = 0; index <=3; ++ index) {
        chosn[index] = 0 ;
     }
     l.lr.up.anmc.sco1.setText(" ");
     l.lr.up.anmc.sco2.setText(" ");
     l.lr.up.anmc.sco3.setText(" ");
     l.lr.up.anmc.sco4.setText(" ");

     return ;
  }

  public int filter0(double inumbr) {
        //  output only to .
       int number ;
       int intermed ;
 
//       intermed = (int) (inumbr) ;
//       number = (float) (intermed);
       number = (int) (inumbr);
       return number ;
  }

  public float filter3(double inumbr) {
     //  output only to .001
       float number ;
       int intermed ;
 
       intermed = (int) (inumbr * 1000.) ;
       number = (float) (intermed / 1000. );
       return number ;
  }
 
  public void setUnits() {   // Switching Units
      double alts,alm1s,alm2s,ars,arm1s,arm2s,t4s,t7s; 
      double t4m1s,t4m2s,t7m1s,t7m2s,u0s ; 
      int i1 ;
  
      alts  = altd / lconv1 ;
      alm1s = altmin / lconv1 ;
      alm2s = altmax / lconv1 ;
      ars   = a2d / aconv ;
      arm1s = a2min / aconv ;
      arm2s = a2max / aconv ;
      u0s   = u0d / lconv2 ;
      t4s   = tt4d / tconv ;
      t4m1s = t4min / tconv ;
      t4m2s = t4max / tconv ;
      t7s   = tt7d / tconv ;
      t7m1s = t7min / tconv ;
      t7m2s = t7max / tconv ;
      switch (lunits) {
         case 0:{                   /* English Units */
             lconv1 = 1.0 ; lconv2 = 1.0 ; fconv = 1.0 ; econv = 1.0 ;
             mconv1 = 1.0 ; pconv  = 1.0 ; tconv = 1.0 ; mconv2= 1.0 ;
             tref = 459.7 ;
             mdpnl.mdleft.lwait.setText("Weight-lbs") ;
             if (rngopt == 1) {
                 l.lr.up.anmc.l2.setText("miles") ;
                 l.lr.up.anin.l2.setText("miles") ;
             }
             if (rngopt == 3) {
                 l.lr.up.anmc.l2.setText("miles") ;
                 l.lr.up.anin.l2.setText("miles") ;
             }
             if (rngopt == 11) {
                 l.lr.up.anmc.l2.setText("slugs") ;
                 l.lr.up.anin.l2.setText("slugs") ;
             }
             if (rngopt == 12) {
                 l.lr.up.anmc.l2.setText("ft/sec^2") ;
                 l.lr.up.anin.l2.setText("ft/sec^2") ;
             }
             if (rngopt == 13) {
                 l.lr.up.anmc.l2.setText("ft/sec") ;
                 l.lr.up.anin.l2.setText("ft/sec") ;
             }
             if (rngopt == 15) {
                 l.lr.up.anmc.l2.setText("feet") ;
                 l.lr.up.anin.l2.setText("feet") ;
             }
             if (rngopt == 17) {
                 l.lr.up.anmc.l2.setText("ft/sec") ;
                 l.lr.up.anin.l2.setText("ft/sec") ;
             }
             if (rngopt == 18) {
                 l.lr.up.anmc.l2.setText("feet") ;
                 l.lr.up.anin.l2.setText("feet") ;
             }
             u0max = 1500. ;
             if (entype == 3) u0max = 4500. ;
             g0d = 32.2 ;
             break ;
          }
          case 1:{                   /* Metric Units */
             lconv1 = .3048 ; lconv2 = 1.609 ; fconv = 4.448 ;
             econv = .000948;
             mconv1 = .4536 ; pconv  = 6.891 ; tconv = 0.555555 ;
             mconv2 = 14.59;    tref = 273.1 ;
             u0max = 2500. ;
             if (entype == 3) u0max = 7500. ;
             g0d = 9.81 ;
             mdpnl.mdleft.lwait.setText("Weight-N") ;
             if (rngopt == 1) {
                 l.lr.up.anmc.l2.setText("km") ;
                 l.lr.up.anin.l2.setText("km") ;
             }
             if (rngopt == 3) {
                 l.lr.up.anmc.l2.setText("km") ;
                 l.lr.up.anin.l2.setText("km") ;
             }
             if (rngopt == 11) {
                 l.lr.up.anmc.l2.setText("kg") ;
                 l.lr.up.anin.l2.setText("kg") ;
             }
             if (rngopt == 12) {
                 l.lr.up.anmc.l2.setText("m/sec^2") ;
                 l.lr.up.anin.l2.setText("m/sec^2") ;
             }
             if (rngopt == 13) {
                 l.lr.up.anmc.l2.setText("m/sec") ;
                 l.lr.up.anin.l2.setText("m/sec") ;
             }
             if (rngopt == 15) {
                 l.lr.up.anmc.l2.setText("meters") ;
                 l.lr.up.anin.l2.setText("meters") ;
             }
             if (rngopt == 17) {
                 l.lr.up.anmc.l2.setText("m/sec") ;
                 l.lr.up.anin.l2.setText("m/sec") ;
             }
             if (rngopt == 18) {
                 l.lr.up.anmc.l2.setText("meters") ;
                 l.lr.up.anin.l2.setText("meters") ;
             }
             break ;
          }
       }
       aconv = lconv1 * lconv1 ;
       bconv = econv / tconv ;
       cconv = mconv1/ aconv / lconv1 ;

       altd    = alts  * lconv1 ;
       altmin  = alm1s * lconv1 ;
       altmax  = alm2s * lconv1 ;
       a2d     = ars * aconv ;
       a2min   = arm1s * aconv ;
       a2max   = arm2s * aconv ;
       u0d     = u0s * lconv2 ;
       tt4d   = t4s * tconv ;
       t4min  = t4m1s * tconv ;
       t4max  = t4m2s * tconv ;
       tt7d   = t7s * tconv ;
       t7min  = t7m1s * tconv ;
       t7max  = t7m2s * tconv ;

       mdpnl.mdleft.o1.setText(String.valueOf(filter0(fconv*aweight))) ;
       mdpnl.mdright.repaint() ;

       return ;
   }

   class Solver {
 
     Solver () {
     }

     public void setDefaults() {
        int i ;
    
        lunits = 0 ;
        lconv1 = 1.0 ; lconv2 = 1.0 ;fconv  = 1.0 ;mconv1 = 1.0 ;
        pconv  = 1.0 ; econv  = 1.0 ;aconv  = 1.0 ;bconv  = 1.0 ;
        mconv2 = 1.0 ; cconv  = 1.0 ;
        tconv  = 1.0 ;  tref = 459.6;
        g0 = g0d = 32.2 ;

        counter = 0 ;
        showcom = 0 ;
        plttyp  = 0 ;
        pltkeep = 0 ;
        planetyp = 0 ;
        fortyp = 0 ;
        plkeep  = 0 ;
        entype  = 0 ;
        inflag  = 0 ;
        pt2flag = 0 ;
        gama = 1.4 ;
        gamopt = 1 ;

        for (i=0; i<=19; ++i) {
             trat[i] = 1.0 ;
             tt[i]   = 518.6 ;
             prat[i] = 1.0 ;
             pt[i]   = 14.7 ;
             eta[i]  = 1.0 ;
        }
        tt[4] = tt4 = tt4d = 2500. ;
        tt[7] = tt7 = tt7d = 3000. ;
        prat[3] = p3p2d = 8.0 ;
        prat[13] = p3fp2d = 2.0 ;
        byprat = 1.0 ;
        abflag = 0 ;
    
        fhv = 18600. ;
        a2d = a2 = acore = 2.0 ;
        ac = .9*a2 ;
        a8rat = .35 ;
        a8 = .7 ;
        a8d = .40 ;
        arsched = 0 ;
        afan = 2.0 ;
        a4 = .418 ;
 
        u0min  = 0.0 ;   u0max = 1500.;
        altmin = 0.0 ;   altmax = 60000. ;
        thrmin = 30;     thrmax = 100 ;
        etmin  = .5;     etmax  = 1.0 ;
        cprmin = 1.0;   cprmax = 50.0 ;
        bypmin = 0.0;   bypmax = 10.0 ;
        fprmin = 1.0;   fprmax = 2.0 ;
        t4min = 1000.0;   t4max = 3200.0 ;
        t7min = 1000.0;   t7max = 3200.0 ;
        a8min = 0.1;      a8max = 0.4 ;
        a2min = .2;       a2max = 50.;
        vmn1 = u0min ;     vmx1 = u0max ;
        vmn2 = altmin ;    vmx2 = altmax ;
        vmn3 = thrmin ;    vmx3 = thrmax ;
 
        antrig = 3 ;
        rngopt = 1 ;
        playflg = 1 ;
        prtflg = 0 ;
        probflg = 0 ;
        prmode = 0 ;
        curcal = 1 ;
        trymax = 3 ;
        trys = 3 ;
        warnflag = 0 ;
        drag = 4836. ;
        numeng = 2 ;
        fueload = 5000. ;
        rngcal = 0 ;
        alt = altd = 30000. ;
        u0d  = ru0  = 400.0 ;
        throtl =  80. ; thrmin = 30.; thrmax = 100.0 ;
        rgoal = 500. ;
        aspecr = 7.0 ; winga = 322.; eff = .8 ;
        aweight = 12500.;
        q0 = 153. ;
        cl = aweight / (q0 * winga);
        cd = drag / (q0 * winga) ;
        cd0  = cd - cl*cl/(3.14159 * aspecr * eff) ;
        if (cd0 < 0.0) cd0 = 0.0 ;

        return ;
     }

     public void loadFan() {

        abflag = 0 ;
        fhv = 18600. ;
        tt[4] = tt4 = tt4d = 2507. ;
        tt[7] = tt7 = tt7d = 3000. ;
        prat[3] = p3p2d = 22.66 ;
        prat[13] = p3fp2d = 1.745 ;
        byprat = 3.3 ;
        acore = 6.965 ;
        afan = acore * (1.0 + byprat) ;
        a2d = a2 = afan ;
        a4 = .290 ;
        a4p = 1.131 ;
        ac = .9*a2 ;
        gama = 1.4 ;
        gamopt = 1 ;
        pt2flag = 0 ;
        eta[2] = 1.0 ;
        prat[2] = 1.0 ;
        prat[4] = 1.0 ;
        eta[3] = .959 ;
        eta[4] = .984 ;
        eta[5] = .982 ;
        eta[7] = 1.0 ;
        eta[13] = 1.0 ;
        a8d = 2.436 ; 
        a8max = .35 ;
        a8rat = .35 ;
   
        u0max = 1500. ;
        u0d = 0.0 ;
        altd = 0.0 ;
        arsched = 0 ;
   
        return ;
     }

     public void loadExec() {
   
        abflag = 0 ;
        fhv = 18600. ;
        tt[4] = tt4 = tt4d = 2260. ;
        tt[7] = tt7 = tt7d = 2600. ;
        prat[3] = p3p2d = 8.3 ;
        prat[13] = p3fp2d = 1.0 ;
        byprat = 0.0 ;
        a2d = a2 = acore = 1.753 ;
        afan = acore * (1.0 + byprat) ;
        a4 = .323 ;
        a4p = .818 ;
        ac = .9*a2 ;
        gama = 1.4 ;
        gamopt = 1 ;
        pt2flag = 0 ;
        eta[2] = 1.0 ;
        prat[2] = 1.0 ;
        prat[4] = 1.0 ;
        eta[3] = .822 ;
        eta[4] = .982 ;
        eta[5] = .882;
        eta[7] = .978 ;
        eta[13] = 1.0 ;
        a8d = .818 ; 
        a8max = 1.753 ;
        a8rat = .467 ;

        u0max = 1500. ;
        u0d = 0.0 ;
        altd = 0.0 ;
        arsched = 0 ;

        return ;
     }

     public void loadFite() {
   
        abflag = 1 ;
        fhv = 18600. ;
        tt[4] = tt4 = tt4d = 2507. ;
        tt[7] = tt7 = tt7d = 2905. ;
        prat[3] = p3p2d = 20.04 ;
        prat[13] = p3fp2d = 1.745 ;
        byprat = 0.0 ;
        a2d = a2 = acore = 6.00 ;
        afan = acore * (1.0 + byprat) ;
        a4 = .472 ;
        a4p = 1.524 ;
        ac = .9*a2 ;
        gama = 1.4 ;
        gamopt = 1 ;
        pt2flag = 0 ;
        eta[2] = 1.0 ;
        prat[2] = 1.0 ;
        prat[4] = 1.0 ;
        eta[3] = .959 ;
        eta[4] = .984 ;
        eta[5] = .982 ;
        eta[7] = 1.0 ;
        eta[13] = 1.0 ;
        a8d = 1.524 ; 
        a8max = .335 ;
        a8rat = .335 ;
   
        u0max = 1500. ;
        u0d = 0.0 ;
        altd = 0.0 ;
        arsched = 0 ;
   
        return ;
     }

     public void loadRam() {
  
        abflag = 0 ;
        fhv = 18600. ;
        tt[4] = tt4 = tt4d = 4000. ;
        tt[7] = tt7 = tt7d = 4000. ;
        prat[3] = p3p2d = 1.0 ;
        prat[13] = p3fp2d = 1.0 ;
        byprat = 0.0 ;
        a2d = a2 = acore = 1.0 ;
        afan = acore * (1.0 + byprat) ;
        a4 = .323 ;
        a4p = .818 ;
        ac = .9*a2 ;
        gama = 1.4 ;
        gamopt = 1 ;
        pt2flag = 0 ;
        eta[2] = 1.0 ;
        prat[2] = 1.0 ;
        prat[4] = 1.0 ;
        eta[3] = 1.0 ;
        eta[4] = .982 ;
        eta[5] = 1.0 ;
        eta[7] = 1.0 ;
        eta[13] = 1.0 ;
        a8 = a8d = 2.00 ;
        a8max = 15. ;
        a8rat = 4.0 ;
        a7 = .50 ;

        u0max = 4500. ;
        u0d = 2200.0 ;
        altd = 10000.0 ;
        arsched = 0 ;

        return ;
     }

     public void getFreeStream() {
       rgas = 1718. ;                /* ft2/sec2 R */
       alt = altd / lconv1  ;
       if (alt < 36000. ) {
          ts0 = 518.6 - 3.56 * alt / 1000. ;
          ps0 = 2116. * Math.pow(ts0/518.6, 5.256) ;
       }
       else {
          ts0 = 389.98 ;
          ps0 = 2116. * .2236 * Math.exp((36000.-alt)/(53.35*389.98)) ;
       }
       a0 = Math.sqrt(gama*rgas*ts0) ;             /* speed of sound ft/sec */
       u0 = u0d /lconv2 *5280./3600. ;                 /* airspeed ft/sec */
       fsmach = u0/a0 ;
       q0 = gama / 2.0*fsmach*fsmach*ps0 ;
       if (u0 > .0001) rho0 = q0 /(u0*u0) ;
       else rho0 = 1.0 ;

       tt[0] = ts0*(1.0 + .5 * (gama -1.0) * fsmach * fsmach) ;
       pt[0] = ps0 * Math.pow(tt[0]/ts0,gama/(gama-1.0)) ;
       ps0 = ps0 / 144. ;
       pt[0] = pt[0] / 144. ;
       cpair = getCp(tt[0],gamopt);              /*BTU/lbm R */
       tsout = ts0 ;
       psout = ps0 ;
   
       return ;
     }
 
     public void getThermo() {
       double dela,t5t4n,deriv,delan,m5;
       double delhc,delhht,delhf,delhlt;
       double deltc,deltht,deltf,deltlt;
       int itcount,index ;
       float fl1 ;
       int i1 ;
                                         /*   inlet recovery  */
       if (pt2flag == 0) {                    /*     mil spec      */
          if (fsmach > 1.0 ) {          /* supersonic */
             prat[2] = 1.0 - .075*Math.pow(fsmach - 1.0, 1.35) ;
          }
          else {
             prat[2] = 1.0 ;
          }
          eta[2] = prat[2] ;
       }
       else {                       /* enter value */
          prat[2] = eta[2] ;          
       }
                               /* protection for overwriting input */
       if (eta[3] < .5) eta[3] = .5 ;
       if (eta[5] < .5) eta[5] = .5 ;
       trat[7] = 1.0 ;
       prat[7] = 1.0 ;
       tt[2] = tt[1] = tt[0] ;
       pt[1] = pt[0] ;
       gam[2] = getGama(tt[2],gamopt) ;
       cp[2]  = getCp(tt[2],gamopt);
       pt[2] = pt[1] * prat[2] ;
        /* design - p3p2 specified - tt4 specified */
       if(inflag == 0) {
   
        if (entype <= 1) {              /* turbojet */
          prat[3] = p3p2d ;                      /* core compressor */
          if (prat[3] < .5) prat[3] = .5 ;
          delhc = (cp[2]*tt[2]/eta[3])*
                (Math.pow(prat[3],(gam[2]-1.0)/gam[2])-1.0) ;
          deltc = delhc / cp[2] ;
          pt[3] = pt[2] * prat[3] ;
          tt[3] = tt[2] + deltc ;
          trat[3] = tt[3]/tt[2] ;
          gam[3] = getGama(tt[3],gamopt) ;
          cp[3]  = getCp(tt[3],gamopt);
          tt[4] = tt4 * throtl/100.0 ;
          gam[4] = getGama(tt[4],gamopt) ;
          cp[4]  = getCp(tt[4],gamopt);
          trat[4] = tt[4] / tt[3] ;
          pt[4] = pt[3] * prat[4] ;
          delhht = delhc ;
          deltht = delhht / cp[4] ;
          tt[5] = tt[4] - deltht ;
          gam[5] = getGama(tt[5],gamopt) ;
          cp[5]  = getCp(tt[5],gamopt);
          trat[5] = tt[5]/tt[4] ;
          prat[5] = Math.pow((1.0-delhht/cp[4]/tt[4]/eta[5]),
                   (gam[4]/(gam[4]-1.0))) ;
          pt[5] = pt[4] * prat[5] ;
                                        /* fan conditions */
          prat[13] = 1.0 ;
          trat[13] = 1.0 ;
          tt[13]   = tt[2] ;
          pt[13]   = pt[2] ;
          gam[13]  = gam[2] ;
          cp[13]   = cp[2] ;
          prat[15] = 1.0 ;
          pt[15]   = pt[5] ;
          trat[15] = 1.0 ;
          tt[15]   = tt[5] ;
          gam[15]  = gam[5] ;
          cp[15]   = cp[5] ;
       }
   
       if(entype == 2) {                         /* turbofan */
          prat[13] = p3fp2d ;
          if (prat[13] < .5) prat[13] = .5 ;
          delhf = (cp[2]*tt[2]/eta[13])*
                (Math.pow(prat[13],(gam[2]-1.0)/gam[2])-1.0) ;
          deltf = delhf / cp[2] ;
          tt[13] = tt[2] + deltf ;
          pt[13] = pt[2] * prat[13] ;
          trat[13] = tt[13]/tt[2] ;
          gam[13] = getGama(tt[13],gamopt) ;
          cp[13]  = getCp(tt[13],gamopt);
          prat[3] = p3p2d ;                      /* core compressor */
          if (prat[3] < .5) prat[3] = .5 ;
          delhc = (cp[13]*tt[13]/eta[3])*
                (Math.pow(prat[3],(gam[13]-1.0)/gam[13])-1.0) ;
          deltc = delhc / cp[13] ;
          tt[3] = tt[13] + deltc ;
          pt[3] = pt[13] * prat[3] ;
          trat[3] = tt[3]/tt[13] ;
          gam[3] = getGama(tt[3],gamopt) ;
          cp[3]  = getCp(tt[3],gamopt);
          tt[4] = tt4 * throtl/100.0 ;
          pt[4] = pt[3] * prat[4] ;
          gam[4] = getGama(tt[4],gamopt) ;
          cp[4]  = getCp(tt[4],gamopt);
          trat[4] = tt[4]/tt[3] ;
          delhht = delhc ;
          deltht = delhht / cp[4] ;
          tt[5] = tt[4] - deltht ;
          gam[5] = getGama(tt[5],gamopt) ;
          cp[5]  = getCp(tt[5],gamopt);
          trat[5] = tt[5]/tt[4] ;
          prat[5] = Math.pow((1.0-delhht/cp[4]/tt[4]/eta[5]),
                      (gam[4]/(gam[4]-1.0))) ;
          pt[5] = pt[4] * prat[5] ;
          delhlt = (1.0 + byprat) * delhf ;
          deltlt = delhlt / cp[5] ;
          tt[15] = tt[5] - deltlt ;
          gam[15] = getGama(tt[15],gamopt) ;
          cp[15]  = getCp(tt[15],gamopt);
          trat[15] = tt[15]/tt[5] ;
          prat[15] = Math.pow((1.0-delhlt/cp[5]/tt[5]/eta[5]),
                      (gam[5]/(gam[5]-1.0))) ;
          pt[15] = pt[5] * prat[15] ;
        }
 
        if (entype == 3) {              /* ramjet */
          prat[3] = 1.0 ;
          pt[3] = pt[2] * prat[3] ;
          tt[3] = tt[2] ;
          trat[3] = 1.0 ;
          gam[3] = getGama(tt[3],gamopt) ;
          cp[3]  = getCp(tt[3],gamopt);
          tt[4] = tt4 * throtl/100.0 ;
          gam[4] = getGama(tt[4],gamopt) ;
          cp[4]  = getCp(tt[4],gamopt);
          trat[4] = tt[4] / tt[3] ;
          pt[4] = pt[3] * prat[4] ;
          tt[5] = tt[4] ;
          gam[5] = getGama(tt[5],gamopt) ;
          cp[5]  = getCp(tt[5],gamopt);
          trat[5] = 1.0 ;
          prat[5] = 1.0 ;
          pt[5] = pt[4] ;
                                        /* fan conditions */
          prat[13] = 1.0 ;
          trat[13] = 1.0 ;
          tt[13]   = tt[2] ;
          pt[13]   = pt[2] ;
          gam[13]  = gam[2] ;
          cp[13]   = cp[2] ;
          prat[15] = 1.0 ;
          pt[15]   = pt[5] ;
          trat[15] = 1.0 ;
          tt[15]   = tt[5] ;
          gam[15]  = gam[5] ;
          cp[15]   = cp[5] ;
        }

        tt[7] = tt7 ;
      }
             /* analysis -assume flow choked at both turbine entrances */
                                  /* and nozzle throat ... then*/
      else {
        tt[4] = tt4 * throtl/100.0 ;
        gam[4] = getGama(tt[4],gamopt) ;
        cp[4]  = getCp(tt[4],gamopt);
        if (a4 < .02) a4 = .02 ;
   
        if (entype <= 1) {              /* turbojet */
           dela = .2 ;                           /* iterate to get t5t4 */
           trat[5] = 1.0 ;
           t5t4n = .5 ;
           itcount = 0 ;
           while(Math.abs(dela) > .001 && itcount < 20) {
              ++ itcount ;
              delan = a8d/a4 - Math.sqrt(t5t4n)*
                   Math.pow((1.0-(1.0/eta[5])*(1.0-t5t4n)),
                          -gam[4]/(gam[4]-1.0)) ;
              deriv = (delan-dela)/(t5t4n-trat[5]) ;
              dela = delan ;
              trat[5] = t5t4n ;
              t5t4n = trat[5] - dela/deriv ;
           }
           tt[5] = tt[4] * trat[5] ;
           gam[5] = getGama(tt[5],gamopt) ;
           cp[5]  = getCp(tt[5],gamopt);
           deltht = tt[5]- tt[4] ;
           delhht  = cp[4] * deltht ;
           prat[5] = Math.pow((1.0-(1.0/eta[5])*(1.0-trat[5])),
                        gam[4]/(gam[4]-1.0)) ;
           delhc = delhht  ;           /* compressor work */
           deltc = -delhc / cp[2] ;
           tt[3] = tt[2] + deltc ;
           gam[3] = getGama(tt[3],gamopt) ;
           cp[3]  = getCp(tt[3],gamopt);
           trat[3] = tt[3]/tt[2] ;
           prat[3] = Math.pow((1.0+eta[3]*(trat[3]-1.0)),
                        gam[2]/(gam[2]-1.0)) ;
           trat[4] = tt[4]/tt[3] ;
           pt[3]   = pt[2] * prat[3] ;
           pt[4]   = pt[3] * prat[4] ;
           pt[5]   = pt[4] * prat[5] ;
                                        /* fan conditions */
           prat[13] = 1.0 ;
           trat[13] = 1.0 ;
           tt[13]   = tt[2] ;
           pt[13]   = pt[2] ;
           gam[13]  = gam[2] ;
           cp[13]   = cp[2] ;
           prat[15] = 1.0 ;
           pt[15]   = pt[5] ;
           trat[15] = 1.0 ;
           tt[15]   = tt[5] ;
           gam[15]  = gam[5] ;
           cp[15]   = cp[5] ;
        }

        if(entype == 2) {                        /*  turbofan */
           dela = .2 ;                           /* iterate to get t5t4 */
           trat[5] = 1.0 ;
           t5t4n = .5 ;
           itcount = 0 ;
           while(Math.abs(dela) > .001 && itcount < 20) {
              ++ itcount ;
              delan = a4p/a4 - Math.sqrt(t5t4n)*
                      Math.pow((1.0-(1.0/eta[5])*(1.0-t5t4n)),
                          -gam[4]/(gam[4]-1.0)) ;
              deriv = (delan-dela)/(t5t4n-trat[5]) ;
              dela = delan ;
              trat[5] = t5t4n ;
              t5t4n = trat[5] - dela/deriv ;
           }
           tt[5] = tt[4] * trat[5] ;
           gam[5] = getGama(tt[5],gamopt) ;
           cp[5]  = getCp(tt[5],gamopt);
           deltht = tt[5] - tt[4] ;
           delhht  = cp[4] * deltht ;
           prat[5] = Math.pow((1.0-(1.0/eta[5])*(1.0-trat[5])),
                     gam[4]/(gam[4]-1.0)) ;
                                       /* iterate to get t15t14 */
           dela = .2 ;
           trat[15] = 1.0 ;
           t5t4n = .5 ;
           itcount = 0 ;
           while(Math.abs(dela) > .001 && itcount < 20) {
               ++ itcount ;
               delan = a8d/a4p - Math.sqrt(t5t4n)*
                        Math.pow((1.0-(1.0/eta[5])*(1.0-t5t4n)),
                          -gam[5]/(gam[5]-1.0)) ;
               deriv = (delan-dela)/(t5t4n-trat[15]) ;
               dela = delan ;
               trat[15] = t5t4n ;
               t5t4n = trat[15] - dela/deriv ;
           }
           tt[15] = tt[5] * trat[15] ;
           gam[15] = getGama(tt[15],gamopt) ;
           cp[15]  = getCp(tt[15],gamopt);
           deltlt = tt[15] - tt[5] ;
           delhlt = cp[5] * deltlt ;
           prat[15] = Math.pow((1.0-(1.0/eta[5])*(1.0-trat[15])),
                       gam[5]/(gam[5]-1.0)) ;
           byprat =  afan / acore - 1.0  ;
           delhf = delhlt / (1.0 + byprat) ;              /* fan work */
           deltf =  - delhf / cp[2] ;
           tt[13] = tt[2] + deltf ;
           gam[13] = getGama(tt[13],gamopt) ;
           cp[13]  = getCp(tt[13],gamopt);
           trat[13] = tt[13]/tt[2] ;
           prat[13] = Math.pow((1.0+eta[13]*(trat[13]-1.0)),
                     gam[2]/(gam[2]-1.0)) ;
           delhc = delhht  ;                         /* compressor work */
           deltc = -delhc / cp[13] ;
           tt[3] = tt[13] + deltc ;
           gam[3] = getGama(tt[3],gamopt) ;
           cp[3]  = getCp(tt[3],gamopt);
           trat[3] = tt[3]/tt[13] ;
           prat[3] = Math.pow((1.0+eta[3]*(trat[3]-1.0)),
                        gam[13]/(gam[13]-1.0)) ;
           trat[4] = tt[4]/tt[3] ;
           pt[13]  = pt[2]  * prat[13] ;
           pt[3]   = pt[13] * prat[3] ;
           pt[4]   = pt[3]  * prat[4] ;
           pt[5]   = pt[4]  * prat[5] ;
           pt[15]  = pt[5]  * prat[15] ;
         }

         if (entype == 3) {              /* ramjet */
           prat[3] = 1.0 ;
           pt[3] = pt[2] * prat[3] ;
           tt[3] = tt[2] ;
           trat[3] = 1.0 ;
           gam[3] = getGama(tt[3],gamopt) ;
           cp[3]  = getCp(tt[3],gamopt);
           trat[4] = tt[4] / tt[3] ;
           pt[4] = pt[3] * prat[4] ;
           tt[5] = tt[4] ;
           gam[5] = getGama(tt[5],gamopt) ;
           cp[5]  = getCp(tt[5],gamopt);
           trat[5] = 1.0 ;
           prat[5] = 1.0 ;
           pt[5] = pt[4] ;
                                         /* fan conditions */
           prat[13] = 1.0 ;
           trat[13] = 1.0 ;
           tt[13]   = tt[2] ;
           pt[13]   = pt[2] ;
           gam[13]  = gam[2] ;
           cp[13]   = cp[2] ;
           prat[15] = 1.0 ;
           pt[15]   = pt[5] ;
           trat[15] = 1.0 ;
           tt[15]   = tt[5] ;
           gam[15]  = gam[5] ;
           cp[15]   = cp[5] ;
         }

         if (abflag == 1) tt[7] = tt7 ;
       }
   
       prat[6] = 1.0;
       pt[6] = pt[15];
       trat[6] = 1.0 ;
       tt[6] = tt[15] ;
       gam[6] = getGama(tt[6],gamopt) ;
       cp[6]  = getCp(tt[6],gamopt);
       if (abflag > 0) {                   /* afterburner */
             trat[7] = tt[7] / tt[6] ;
             m5 = getMach(0,getAir(1.0,gam[5])*a4/acore,gam[5]) ;
             prat[7] = getRayleighLoss(m5,trat[7],tt[6]) ;
       }
       tt[7] = tt[6] * trat[7] ;
       pt[7] = pt[6] * prat[7] ;
       gam[7] = getGama(tt[7],gamopt) ;
       cp[7]  = getCp(tt[7],gamopt);
                 /* engine press ratio EPR*/
       epr = prat[7]*prat[15]*prat[5]*prat[4]*prat[3]*prat[13];
              /* engine temp ratio ETR */
       etr = trat[7]*trat[15]*trat[5]*trat[4]*trat[3]*trat[13];
       return;
     }
   
     public void getPerform ()  {       /* determine engine performance */
       double fac1,game,cpe,cp3,t8,rg,p8p5,rg1 ;
       double mexit,pexit ;
       int index ;
   
       rg1 = 53.3 ;
       rg = cpair * (gama-1.0)/gama ;
       cp3 = getCp(tt[3],gamopt);                  /*BTU/lbm R */
       g0 = 32.2 ;
       ues = 0.0 ;
       game = getGama(tt[5],gamopt) ;
       fac1 = (game - 1.0)/game ;
       cpe = getCp(tt[5],gamopt) ;
       if (eta[7] < .8) eta[7] = .8 ;    /* protection during overwriting */
       if (eta[4] < .8) eta[4] = .8 ;
       if (entype <=2) {      /*   for turbine engines  */
                            /* core airflow determined at choked nozzle */
            eair = getAir(1.0,game) * 144.*a8 * epr*prat[2]*pt[0]/14.7 /
                    Math.sqrt(etr*tt[0]/518.)   ;
       }
       if (entype == 3) {  /* for ramjets - airflow determined at the inlet */
            eair = getAir(fsmach,gama) * 144.0 * a2 * pt[0]/14.7 /
                    Math.sqrt(tt[0]/518.)   ;
       }
       m2 = getMach(0,eair*Math.sqrt(tt[0]/518.)/
                    (prat[2]*pt[0]/14.7*acore*144.),gama) ;
                         /* core engine */
       npr = epr*prat[2]*pt[0]/ps0;
       uexit = Math.sqrt(2.0*rgas/fac1*etr*tt[0]*eta[7]*
                    (1.0-Math.pow(1.0/npr,fac1)));
       /*  specific net thrust  - thrust / (g0*airflow) -   lbf/lbm/sec  */
       fgros = uexit/g0 ;
       if (entype == 3) {   // extra term for off-design ramjet
           mexit = Math.sqrt((2.0/(gama-1.0))*((1.0+.5*(gama-1.0)*fsmach*fsmach)                  *Math.pow((epr*prat[2]),(gama-1.0)/gama) - 1.0) ) ;
//           mexit = getMach(2,(getAir(1.0,gama) / a8rat),gama) ;
           uexit = mexit * Math.sqrt(gama * rgas * etr * tt[0] /
                     (1.0 + .5 *(gama-1.0) * mexit *mexit)) ;
           pexit = Math.pow((1.0 + .5 *(gama-1.0) * mexit *mexit),(-gama/(gama-1.0)))
                     * epr * prat[2] * pt[0] ;
           fgros = (uexit + (pexit - ps0)*a8/eair/144.) / g0 ;
       }
       dram = u0 / g0 ;
                            /* fan performance */
       if (entype == 2) {
         fac1 = (gama - 1.0)/gama ;
         snpr = pt[13]/ps0;
         ues = Math.sqrt(2.0*rgas/fac1*tt[13]*eta[7]*
                     (1.0-Math.pow(1.0/snpr,fac1)));
         m2 = getMach(0,eair*(1.0+byprat)*Math.sqrt(tt[0]/518.)/
                     (prat[2]*pt[0]/14.7*afan*144.),gama) ;
         fgros = (uexit + byprat*ues)/g0 ;
         dram = u0 * (1.0 + byprat) / g0 ;
       }
                         /* mass flow ratio */
       if (fsmach > .01) mfr = getAir(m2,gama)*prat[2]/getAir(fsmach,gama) ;
       else mfr = 5.;
       fnet = fgros - dram;
       fnlb = fnet * eair ;
       fglb = fgros * eair ;
       drlb = dram * eair ;
   
       fa = (trat[4]-1.0)/(eta[4]*fhv/(cp3*tt[3])-trat[4]) +
         (trat[7]-1.0)/(fhv/(cpe*tt[15])-trat[7]) ;
       sfc = 3600. * fa /fnet ;
       flflo = sfc*fnlb ;
       tt[8] = t8 = etr * tt[0] - uexit * uexit /(2.0*rgas*gama/(gama-1.0)) ;
       trat[8] = tt[8]/tt[7] ;
       p8p5 = ps0 / (epr * prat[2] *pt[0]) ;
       cp[8] = getCp(tt[8],gamopt) ;
       pt[8] = pt[7] ;
       prat[8] = pt[8]/pt[7] ;
        /* thermal effeciency */
       if (entype == 2) {
           eteng = (a0*a0*((1.0+fa)*(uexit*uexit/(a0*a0))
           + byprat*(ues*ues/(a0*a0))
           - (1.0+byprat)*fsmach*fsmach))/(2.0*g0*fa*fhv*778.16)    ;
       }
       else {
           eteng = (a0*a0*((1.0+fa)*(uexit*uexit/(a0*a0))
           - fsmach*fsmach))/(2.0*g0*fa*fhv*778.16)    ;
       }

       s[0] = s[1] = .2 ;
       v[0] = v[1] = rg1*ts0/(ps0*144.) ;
       for (index=2; index <=7 ; ++index ) {     /* compute entropy */
         s[index] = s[index-1] + cpair*Math.log(trat[index])
                               - rg*Math.log(prat[index])  ;
         v[index] = rg1*tt[index]/(pt[index]*144.) ;
       }
       s[13] = s[2] + cpair*Math.log(trat[13])-rg*Math.log(prat[13]);
       v[13] = rg1*tt[13]/(pt[13]*144.) ;
       s[15] = s[5] + cpair*Math.log(trat[15])-rg*Math.log(prat[15]);
       v[15] = rg1*tt[15]/(pt[15]*144.) ;
       s[8]  = s[7] + cpair*Math.log(t8/(etr*tt[0]))- rg*Math.log(p8p5)  ;
       v[8]  = rg1*tt[8]/(ps0*144.) ;
       cp[0] = getCp(tt[0],gamopt) ;

       fntot   = numeng * fnlb ;
       fuelrat = numeng * flflo ;
     }
 
     public void getGeo () {
                            /* determine geometric variables */
        float fl1 ;
        int i1 ;

        if (entype <= 2) {          // turbine engines
          if (afan < acore) afan = acore ;
          a8max = .75 * Math.sqrt(etr) / epr ; /* limits compressor face  */
                                   /*  mach number  to < .5   */
          if (a8max > 1.0) a8max = 1.0 ;
          if (a8rat > a8max) {
           a8rat = a8max ;
          }
              /*    dumb down limit - a8 schedule */
          if (arsched == 0) {
           a8rat = a8max ;
          }
          a8 = a8rat * acore ;
          a8d = a8 * prat[7] / Math.sqrt(trat[7]) ;
             /* assumes choked a8 and a4 */
          a4 = a8*prat[5]*prat[15]*prat[7]/
           Math.sqrt(trat[7]*trat[5]*trat[15]);
          a4p = a8*prat[15]*prat[7]/Math.sqrt(trat[7]*trat[15]);
          ac = .9 * a2 ;
        }

        if (entype == 3) {      // ramjets
          if (arsched == 0) {
             a8d = a8 = Math.sqrt(etr) * a2 ;
             if (fsmach >= 1.0) {
               a7 = a8 * getAir(fsmach,gama) / getAir(1.0,gama) ;
             }
             if (fsmach < 1.0) {
               a7 = .95 * a8 ;
             }
             a8rat = a8/a7 ;
          }
          if (arsched == 1) {
             a8d = a8 = a8rat * a7 ;
          }
        }
     }

     public double getGama(double temp, int opt) {
                // Utility to get gamma as a function of temp 
         double number,a,b,c,d ;
         a =  -7.6942651e-13;
         b =  1.3764661e-08;
         c =  -7.8185709e-05;
         d =  1.436914;
         if(opt == 0) {
            number = 1.4 ;
         }
         else {
            number = a*temp*temp*temp + b*temp*temp + c*temp +d ;
         }
         return(number) ;
     }

     public double getCp(double temp, int opt)  {
               // Utility to get cp as a function of temp 
         double number,a,b,c,d ;
                                 /* BTU/R */
         a =  -4.4702130e-13;
         b =  -5.1286514e-10;
         c =   2.8323331e-05;
         d =  0.2245283;
         if(opt == 0) {
            number = .2399 ;
         }
         else {
            number = a*temp*temp*temp + b*temp*temp + c*temp +d ;
         }
         return(number) ;
     }

      public double getMach (int sub, double corair, double gamma) {
/* Utility to get the Mach number given the corrected airflow per area */
         double number,chokair;              /* iterate for mach number */
         double deriv,machn,macho,airo,airn;
         int iter ;

         chokair = getAir(1.0, gamma) ;
         if (corair > chokair) {
           number = 1.0 ;
           return (number) ;
         }
         else {
           airo = .25618 ;                 /* initial guess */
           if (sub == 1) macho = 1.0 ;   /* sonic */
           else {
              if (sub == 2) macho = 1.703 ; /* supersonic */
              else macho = .5;                /* subsonic */
              iter = 1 ;
              machn = macho - .2  ;
              while (Math.abs(corair - airo) > .0001 && iter < 20) {
                 airn =  getAir(machn,gamma) ;
                 deriv = (airn-airo)/(machn-macho) ;
                 airo = airn ;
                 macho = machn ;
                 machn = macho + (corair - airo)/deriv ;
                 ++ iter ;
              }
           }
           number = macho ;
         }
         return(number) ;
     }

      public double getRayleighLoss(double mach1, double ttrat, double tlow) {
                                         /* analysis for rayleigh flow */
        double number ;
        double wc1,wc2,mgueso,mach2,g1,gm1,g2,gm2 ;
        double fac1,fac2,fac3,fac4;
   
        g1 = getGama(tlow,gamopt);
        gm1 = g1 - 1.0 ;
        wc1 = getAir(mach1,g1);
        g2 = getGama(tlow*ttrat,gamopt);
        gm2 = g2 - 1.0 ;
        number = .95 ;
                                /* iterate for mach downstream */
        mgueso = .4 ;                 /* initial guess */
        mach2 = .5 ;
        while (Math.abs(mach2 - mgueso) > .0001) {
            mgueso = mach2 ;
            fac1 = 1.0 + g1 * mach1 * mach1 ;
            fac2 = 1.0 + g2 * mach2 * mach2 ;
            fac3 = Math.pow((1.0 + .5 * gm1 * mach1 * mach1),(g1/gm1)) ;
            fac4 = Math.pow((1.0 + .5 * gm2 * mach2 * mach2),(g2/gm2)) ;
            number = fac1 * fac4 / fac2 / fac3 ;
            wc2 = wc1 * Math.sqrt(ttrat) / number ;
            mach2 = getMach(0,wc2,g2) ;
        }
        return(number) ;
     }
 
     public double getAir(double mach, double gamma) {
/* Utility to get the corrected airflow per area given the Mach number */
       double number,fac1,fac2;
       fac2 = (gamma+1.0)/(2.0*(gamma-1.0)) ;
       fac1 = Math.pow((1.0+.5*(gamma-1.0)*mach*mach),fac2);
       number =  .50161*Math.sqrt(gamma) * mach/ fac1 ;
   
       return(number) ;
     }
  }

  class Mdpnl extends Panel {
     Range outerparent ;
     Mdright mdright ;
     Mdleft mdleft ;

     Mdpnl (Range target) { 
          outerparent = target ;
          setLayout(new GridLayout(1,2,0,0)) ;
 
          mdleft = new Mdleft(outerparent) ;
          mdright = new Mdright(outerparent) ;

          add(mdleft) ;  
          add(mdright) ;  
     }

     class Mdleft extends Panel {
// set up the problem types and the options
        Range outerparent ;
        Label lwait,lprob ;
        TextField o1,o2,o3,o4,o5 ;
        Choice acch,unch,plch,anch,levch,probch ;
        Button newpr ;
 
        Mdleft (Range target) {
          outerparent = target ;
          setLayout(new GridLayout(4,4,4,0)) ;
 
          acch = new Choice() ;
          acch.addItem("Exec Jet") ;
          acch.addItem("Fighter");
          acch.addItem("Airliner");
          acch.addItem("Hyper");
          acch.setBackground(Color.black) ;
          acch.setForeground(Color.yellow) ;
          acch.select(0) ;

          unch = new Choice() ;
          unch.addItem("English") ;
          unch.addItem("Metric");
          unch.select(0) ;

          anch = new Choice() ;
          anch.addItem("Choice") ;
          anch.addItem("Type-In");
          anch.select(0) ;

          plch = new Choice() ;
          plch.addItem("Play") ;
          plch.addItem("Learn");
          plch.addItem("Exam");
          plch.select(0) ;

          lprob = new Label("Problem", Label.LEFT);
          lprob.setBackground(Color.white) ;
          lprob.setForeground(Color.blue) ;

          lwait = new Label("Weight-lbs", Label.CENTER);
          o1 = new TextField() ;
          o1.setBackground(Color.black) ;
          o1.setForeground(Color.yellow) ;
//  diagnostics
          o2 = new TextField() ;
          o2.setBackground(Color.black) ;
          o2.setForeground(Color.yellow) ;
          o3 = new TextField() ;
          o4 = new TextField() ;
          o5 = new TextField() ;

          newpr  = new Button("New") ;
          newpr.setBackground(Color.blue) ;
          newpr.setForeground(Color.white) ;

          levch = new Choice() ;
          levch.addItem("Easy") ;
          levch.addItem("Hard");
          levch.select(0) ;
          levch.setBackground(Color.blue) ;
          levch.setForeground(Color.white) ;

          probch = new Choice() ;
          probch.addItem("Range") ;
          probch.addItem("Weight");
          probch.addItem("Motion");
          probch.select(0) ;
          probch.setBackground(Color.blue) ;
          probch.setForeground(Color.white) ;

          add(new Label("Mode:",Label.RIGHT)) ;
          add(plch) ;
          add(new Label("Aircraft ",Label.RIGHT)) ;
          add(acch) ;

          add(new Label("Units ",Label.RIGHT)) ;
          add(unch) ;
          add(lwait) ;
          add(o1) ;

          add(new Label("Answer:",Label.RIGHT)) ;
          add(anch) ;
          add(new Label("Mach ",Label.RIGHT)) ;
          add(o2) ;

          add(lprob) ;
          add(newpr) ;
          add(levch) ;
          add(probch) ;
        }

        public Insets insets() {
           return new Insets(0,0,5,0) ;
        }

        public boolean action(Event evt, Object arg) {
            if(evt.target instanceof Choice) {
               String label = (String)arg ;
               if(label.equals("Exec Jet")) {
                 entype = planetyp = 0 ;
                 solve.loadExec() ;
                 antrig = 3 ;
                 view.start() ;
               }
               if(label.equals("Fighter")) {
                 entype = planetyp = 1 ;
                 solve.loadFite() ;
                 antrig = 3 ;
                 view.start() ;
               }
               if(label.equals("Airliner")) {
                 entype = planetyp = 2 ;
                 solve.loadFan() ;
                 antrig = 3 ;
                 view.start() ;
               }
               if(label.equals("Hyper")) {
                 entype = planetyp = 3 ;
                 solve.loadRam() ;
                 antrig = 3 ;
                 view.start() ;
               }
               if(label.equals("English")) {
                 lunits = 0 ;
                 setUnits() ;
               }
               if(label.equals("Metric")) {
                 lunits = 1 ;
                 setUnits() ;
               }
               if(label.equals("Play")) {
                 playflg = 1 ;
                 prtflg = 0 ;
                 trymax = 3;
                 nrong = 0 ;
                 nrite = 0 ;
                 l.lr.dwn.d2.stat.setText("Have Fun !") ;
                 l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                 l.lr.dwn.d3.rong.setText(String.valueOf(nrong));
                 l.lr.dwn.d3.rite.setText(String.valueOf(nrite));
               }
               if(label.equals("Learn")) {
                 playflg = 2 ;
                 prtflg = 1 ;
                 trymax = 3;
                 nrong = 0 ;
                 nrite = 0 ;
                 l.lr.dwn.d2.stat.setText("Answers will be recorded") ;
                 l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                 l.lr.dwn.d3.rong.setText(String.valueOf(nrong));
                 l.lr.dwn.d3.rite.setText(String.valueOf(nrite));
               }
               if(label.equals("Exam")) {
                 playflg = 3 ;
                 prtflg = 1 ;
                 trymax = 1;
                 trys = trymax ;
                 nrong = 0 ;
                 nrite = 0 ;
                 l.lr.dwn.d2.stat.setText("Only one chance at each problem !") ;
                 l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                 l.lr.dwn.d3.rong.setText(String.valueOf(nrong));
                 l.lr.dwn.d3.rite.setText(String.valueOf(nrite));
               }
               if(label.equals("Choice")) {
                 prmode = 0 ;
                 layout.show(l.lr.up, "first")  ;
               }
               if(label.equals("Type-In")) {
                 prmode = 1 ;
                 layout.show(l.lr.up, "second")  ;
               }
               if(label.equals("Easy")) {
                 fortyp = 0 ;
                 if (probflg == 0) {
                     probin.show(l.ll.pr, "first")  ;
                     rngopt = 1 ;
                     l.ll.pr.rngprb1.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Range") ;
                     l.lr.up.anin.l1.setText("Range") ;
                     if(lunits == 0) {
                          l.lr.up.anmc.l2.setText("miles") ;
                          l.lr.up.anin.l2.setText("miles") ;
                     }
                     if(lunits == 1) {
                          l.lr.up.anmc.l2.setText("km") ;
                          l.lr.up.anin.l2.setText("km") ;
                     }
                 }
                 if (probflg == 1) {
                     probin.show(l.ll.pr, "third")  ;
                     rngopt = 11 ;
                     l.ll.pr.watprb.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Mass") ;
                     l.lr.up.anin.l1.setText("Mass") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("slugs") ;
                         l.lr.up.anin.l2.setText("slugs") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("kg") ;
                         l.lr.up.anin.l2.setText("kg") ;
                     }
                 }
                 if (probflg == 2) {
                     probin.show(l.ll.pr, "fourth")  ;
                     rngopt = 12 ;
                     l.ll.pr.motprb1.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Accel") ;
                     l.lr.up.anin.l1.setText("Accel") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec^2") ;
                         l.lr.up.anin.l2.setText("ft/sec^2") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec^2") ;
                         l.lr.up.anin.l2.setText("m/sec^2") ;
                     }
                 }
               }
               if(label.equals("Hard")) {
                 fortyp = 1 ;
                 if (probflg == 0) {
                     probin.show(l.ll.pr, "second")  ;
                     rngopt = 3 ;
                     l.ll.pr.rngprb2.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Range") ;
                     l.lr.up.anin.l1.setText("Range") ;
                     if(lunits == 0) {
                          l.lr.up.anmc.l2.setText("miles") ;
                          l.lr.up.anin.l2.setText("miles") ;
                     }
                     if(lunits == 1) {
                          l.lr.up.anmc.l2.setText("km") ;
                          l.lr.up.anin.l2.setText("km") ;
                     }
                 }
                 if (probflg == 1) {
                     probin.show(l.ll.pr, "third")  ;
                     rngopt = 11 ;
                     l.ll.pr.watprb.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Mass") ;
                     l.lr.up.anin.l1.setText("Mass") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("slugs") ;
                         l.lr.up.anin.l2.setText("slugs") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("kg") ;
                         l.lr.up.anin.l2.setText("kg") ;
                     }
                 }
                 if (probflg == 2) {
                     probin.show(l.ll.pr, "fifth")  ;
                     rngopt = 12 ;
                     l.ll.pr.motprb2.prch.select(0) ;
                     l.lr.up.anmc.l1.setText("Accel") ;
                     l.lr.up.anin.l1.setText("Accel") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec^2") ;
                         l.lr.up.anin.l2.setText("ft/sec^2") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec^2") ;
                         l.lr.up.anin.l2.setText("m/sec^2") ;
                     }
                 }
               }
               if(label.equals("Range")) {
                 probflg = 0 ;
                 l.lr.up.anmc.l1.setText("Range") ;
                 l.lr.up.anin.l1.setText("Range") ;
                 if(lunits == 0) {
                      l.lr.up.anmc.l2.setText("miles") ;
                      l.lr.up.anin.l2.setText("miles") ;
                 }
                 if(lunits == 1) {
                      l.lr.up.anmc.l2.setText("km") ;
                      l.lr.up.anin.l2.setText("km") ;
                 }
                 if (fortyp == 0) {
                     probin.show(l.ll.pr, "first")  ;
                     rngopt = 1 ;
                     l.ll.pr.rngprb1.prch.select(0) ;
                 }
                 if (fortyp == 1) {
                     probin.show(l.ll.pr, "second")  ;
                     rngopt = 3 ;
                     l.ll.pr.rngprb2.prch.select(0) ;
                 }
               }
               if(label.equals("Weight")) {
                 probflg = 1 ;
                 probin.show(l.ll.pr, "third")  ;
                 rngopt = 11 ;
                 l.ll.pr.watprb.prch.select(0) ;
                 l.lr.up.anmc.l1.setText("Mass") ;
                 l.lr.up.anin.l1.setText("Mass") ;
                 if(lunits == 0) {
                     l.lr.up.anmc.l2.setText("slugs") ;
                     l.lr.up.anin.l2.setText("slugs") ;
                 }
                 if(lunits == 1) {
                     l.lr.up.anmc.l2.setText("kg") ;
                     l.lr.up.anin.l2.setText("kg") ;
                 }
               }
               if(label.equals("Motion")) {
                 probflg = 2 ;
                 l.lr.up.anmc.l1.setText("Accel") ;
                 l.lr.up.anin.l1.setText("Accel") ;
                 if(lunits == 0) {
                     l.lr.up.anmc.l2.setText("ft/sec^2") ;
                     l.lr.up.anin.l2.setText("ft/sec^2") ;
                 }
                 if(lunits == 1) {
                     l.lr.up.anmc.l2.setText("m/sec^2") ;
                     l.lr.up.anin.l2.setText("m/sec^2") ;
                 }
                 if (fortyp == 0) {
                     probin.show(l.ll.pr, "fourth")  ;
                     rngopt = 12 ;
                     l.ll.pr.motprb1.prch.select(0) ;
                 }
                 if (fortyp == 1) {
                     probin.show(l.ll.pr, "fifth")  ;
                     rngopt = 12 ;
                     l.ll.pr.motprb2.prch.select(0) ;
                 }
               }

               curcal = 1 ;
               computeRange() ;
               return true ;
            }
            if(evt.target instanceof Button) {
               String label = (String)arg ;
               if(label.equals("New")) {
                 curcal = 1 ;
                 trys = trymax ;
                 l.lr.dwn.d3.remain.setText(String.valueOf(trys));
                 l.lr.dwn.d2.stat.setText("New Problem Has Been Set") ;
                 l.lr.up.anin.inp1.setText("       ?") ;
                 antrig = 0 ;
                 counter = 0 ;
               }
               computeRange () ;
               return true ;
            }
            else return false ;
        }
     }

     class Mdright extends Canvas {

        Mdright (Range target) {
         setBackground(Color.black) ;
        }
 
        public void update(Graphics g) {
          mdright.paint(g) ;
        }
   
        public void paint(Graphics g) {
            double scale ;
            int index;
            int ex[] = new int[3] ;
            int ey[] = new int[3] ;
            scale = .001 ;
            off2Gg.setColor(Color.white) ;
            off2Gg.fillRect(0,0,300,120) ;
                // altitude
            off2Gg.setColor(Color.black) ;
            off2Gg.fillRect(0,0,70,120) ;
            off2Gg.setColor(Color.white) ;
            off2Gg.drawLine(40,20,68,20) ;
            off2Gg.drawLine(40,35,68,35) ;
            off2Gg.drawLine(40,50,68,50) ;
            off2Gg.drawLine(40,65,68,65) ;
            off2Gg.drawLine(40,80,68,80) ;
            off2Gg.drawString("  0  ",0,100);
            off2Gg.drawLine(40,95,68,95) ;
            ex[0] = 40 ;
            ex[1] = 50 ;
            ex[2] = 50 ;
            if (lunits == 0) {
               off2Gg.drawString("50000",0,25);
               off2Gg.drawString("40000",0,40);
               off2Gg.drawString("30000",0,55);
               off2Gg.drawString("20000",0,70);
               off2Gg.drawString("10000",0,85);
               scale = (95. - 20.)/ 50000. ;
               ey[0] = 20 + (int) (scale*(50000. - altd)) ;
            }
            if (lunits == 1) {
               off2Gg.drawString("15000",0,25);
               off2Gg.drawString("12000",0,40);
               off2Gg.drawString(" 9000",0,55);
               off2Gg.drawString(" 6000",0,70);
               off2Gg.drawString(" 3000",0,85);
               scale = (95. - 20.)/ 15000. ;
               ey[0] = 20 + (int) (scale*(15000. - altd)) ;
            }
            ey[1] = ey[0] - 5 ;
            ey[2] = ey[0] + 5 ;
            off2Gg.setColor(Color.yellow) ;
            off2Gg.fillRect(ex[1],ey[1]+2,20,6) ;
            off2Gg.fillPolygon(ex,ey,3) ;
            off2Gg.drawString(String.valueOf(filter0(altd)),10,115);
            off2Gg.setColor(Color.green) ;
            if(lunits == 0) off2Gg.drawString("Altitude - ft",5,10);
            if(lunits == 1) off2Gg.drawString("Altitude - m",5,10);
                // speed
            off2Gg.setColor(Color.black) ;
            off2Gg.fillRect(75,0,68,120) ;
            off2Gg.setColor(Color.white) ;
            off2Gg.drawLine(110,20,142,20) ;
            off2Gg.drawLine(110,35,142,35) ;
            off2Gg.drawLine(110,50,142,50) ;
            off2Gg.drawLine(110,65,142,65) ;
            off2Gg.drawLine(110,80,142,80) ;
            off2Gg.drawString("  0  ",80,100);
            off2Gg.drawLine(110,95,142,95) ;
            ex[0] = 110 ;
            ex[1] = 120 ;
            ex[2] = 120 ;
            if (planetyp == 0 || planetyp == 2) {
               if (lunits == 0) {
                  off2Gg.drawString(" 625 ",80,25);
                  off2Gg.drawString(" 500 ",80,40);
                  off2Gg.drawString(" 375 ",80,55);
                  off2Gg.drawString(" 250 ",80,70);
                  off2Gg.drawString(" 125 ",80,85);
                  scale = (95. - 20.)/ 625. ;
                  ey[0] = 20 + (int) (scale*(625. - u0d)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("1250 ",80,25);
                  off2Gg.drawString("1000 ",80,40);
                  off2Gg.drawString(" 750 ",80,55);
                  off2Gg.drawString(" 500 ",80,70);
                  off2Gg.drawString(" 250 ",80,85);
                  scale = (95. - 20.)/ 1250. ;
                  ey[0] = 20 + (int) (scale*(1250. - u0d)) ;
               }
            }
            if (planetyp == 1) {
               if (lunits == 0) {
                  off2Gg.drawString("1250 ",80,25);
                  off2Gg.drawString("1000 ",80,40);
                  off2Gg.drawString(" 750 ",80,55);
                  off2Gg.drawString(" 500 ",80,70);
                  off2Gg.drawString(" 250 ",80,85);
                  scale = (95. - 20.)/ 1250. ;
                  ey[0] = 20 + (int) (scale*(1250. - u0d)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("2000 ",80,25);
                  off2Gg.drawString("1600 ",80,40);
                  off2Gg.drawString("1200 ",80,55);
                  off2Gg.drawString(" 800 ",80,70);
                  off2Gg.drawString(" 400 ",80,85);
                  scale = (95. - 20.)/ 2000. ;
                  ey[0] = 20 + (int) (scale*(2000. - u0d)) ;
               }
            }
            if (planetyp == 3) {
               if (lunits == 0) {
                  off2Gg.drawString("5000 ",80,25);
                  off2Gg.drawString("4000 ",80,40);
                  off2Gg.drawString("3000 ",80,55);
                  off2Gg.drawString("2000 ",80,70);
                  off2Gg.drawString("1000 ",80,85);
                  scale = (95. - 20.)/ 5000. ;
                  ey[0] = 20 + (int) (scale*(5000. - u0d)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("10000 ",80,25);
                  off2Gg.drawString("8000 ",80,40);
                  off2Gg.drawString("6000 ",80,55);
                  off2Gg.drawString("4800 ",80,70);
                  off2Gg.drawString("2000 ",80,85);
                  scale = (95. - 20.)/ 10000. ;
                  ey[0] = 20 + (int) (scale*(10000. - u0d)) ;
               }
            }
            ey[1] = ey[0] - 5 ;
            ey[2] = ey[0] + 5 ;
            off2Gg.setColor(Color.yellow) ;
            off2Gg.fillRect(ex[1],ey[1]+2,22,6) ;
            off2Gg.fillPolygon(ex,ey,3) ;
            off2Gg.drawString(String.valueOf(filter0(u0d)),100,115);
            off2Gg.setColor(Color.green) ;
            if(lunits == 0) off2Gg.drawString("Speed-mph",77,10);
            if(lunits == 1) off2Gg.drawString("Speed-kmh",77,10);
                // thrust
            off2Gg.setColor(Color.black) ;
            off2Gg.fillRect(147,0,68,120) ;
            off2Gg.setColor(Color.white) ;
            off2Gg.drawLine(190,20,215,20) ;
            off2Gg.drawLine(190,35,215,35) ;
            off2Gg.drawLine(190,50,215,50) ;
            off2Gg.drawLine(190,65,215,65) ;
            off2Gg.drawLine(190,80,215,80) ;
            off2Gg.drawString("  0  ",150,100);
            off2Gg.drawLine(190,95,215,95) ;
            ex[0] = 190 ;
            ex[1] = 200 ;
            ex[2] = 200 ;
            if (planetyp == 0) {
               if (lunits == 0) {
                  off2Gg.drawString("15000",150,25);
                  off2Gg.drawString("12000",150,40);
                  off2Gg.drawString("9000",150,55);
                  off2Gg.drawString("6000",150,70);
                  off2Gg.drawString("3000",150,85);
                  scale = (95. - 20.)/ 15000. ;
                  ey[0] = 20 + (int) (scale*(15000. - fconv*fntot)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("75000",150,25);
                  off2Gg.drawString("60000",150,40);
                  off2Gg.drawString("45000",150,55);
                  off2Gg.drawString("30000",150,70);
                  off2Gg.drawString("15000",150,85);
                  scale = (95. - 20.)/ 75000. ;
                  ey[0] = 20 + (int) (scale*(75000. - fconv*fntot)) ;
               }
            }
            if (planetyp == 1) {
               if (lunits == 0) {
                  off2Gg.drawString("50000",150,25);
                  off2Gg.drawString("40000",150,40);
                  off2Gg.drawString("30000",150,55);
                  off2Gg.drawString("20000",150,70);
                  off2Gg.drawString("10000",150,85);
                  scale = (95. - 20.)/ 50000. ;
                  ey[0] = 20 + (int) (scale*(50000. - fconv*fntot)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("250000",150,25);
                  off2Gg.drawString("200000",150,40);
                  off2Gg.drawString("150000",150,55);
                  off2Gg.drawString("100000",150,70);
                  off2Gg.drawString("50000",150,85);
                  scale = (95. - 20.)/ 250000. ;
                  ey[0] = 20 + (int) (scale*(250000. - fconv*fntot)) ;
               }
            }
            if (planetyp == 2) {
               if (lunits == 0) {
                  off2Gg.drawString("150000",150,25);
                  off2Gg.drawString("120000",150,40);
                  off2Gg.drawString("90000",150,55);
                  off2Gg.drawString("60000",150,70);
                  off2Gg.drawString("30000",150,85);
                  scale = (95. - 20.)/ 150000. ;
                  ey[0] = 20 + (int) (scale*(150000. - fconv*fntot)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("750000",150,25);
                  off2Gg.drawString("600000",150,40);
                  off2Gg.drawString("450000",150,55);
                  off2Gg.drawString("300000",150,70);
                  off2Gg.drawString("150000",150,85);
                  scale = (95. - 20.)/ 750000. ;
                  ey[0] = 20 + (int) (scale*(750000. - fconv*fntot)) ;
               }
            }
            if (planetyp == 3) {
               if (lunits == 0) {
                  off2Gg.drawString("10000",150,25);
                  off2Gg.drawString("8000",150,40);
                  off2Gg.drawString("6000",150,55);
                  off2Gg.drawString("4000",150,70);
                  off2Gg.drawString("2000",150,85);
                  scale = (95. - 20.)/ 10000. ;
                  ey[0] = 20 + (int) (scale*(10000. - fconv*fntot)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("50000",150,25);
                  off2Gg.drawString("40000",150,40);
                  off2Gg.drawString("30000",150,55);
                  off2Gg.drawString("20000",150,70);
                  off2Gg.drawString("10000",150,85);
                  scale = (95. - 20.)/ 50000. ;
                  ey[0] = 20 + (int) (scale*(50000. - fconv*fntot)) ;
               }
            }
            ey[1] = ey[0] - 5 ;
            ey[2] = ey[0] + 5 ;
            off2Gg.setColor(Color.yellow) ;
            off2Gg.fillRect(ex[1],ey[1]+2,15,6) ;
            off2Gg.fillPolygon(ex,ey,3) ;
            off2Gg.drawString(String.valueOf(filter0(fconv*fntot)),160,115);
            off2Gg.setColor(Color.green) ;
            if(lunits == 0) off2Gg.drawString("Thrust - lb",155,10);
            if(lunits == 1) off2Gg.drawString("Thrust - N",155,10);
                // Fuel flow
            off2Gg.setColor(Color.black) ;
            off2Gg.fillRect(219,0,70,120) ;
            off2Gg.setColor(Color.white) ;
            off2Gg.drawLine(260,20,288,20) ;
            off2Gg.drawLine(260,35,288,35) ;
            off2Gg.drawLine(260,50,288,50) ;
            off2Gg.drawLine(260,65,288,65) ;
            off2Gg.drawLine(260,80,288,80) ;
            off2Gg.drawString("  0  ",220,100);
            off2Gg.drawLine(260,95,288,95) ;
            ex[0] = 260 ;
            ex[1] = 270 ;
            ex[2] = 270 ;
            if (planetyp == 0 ) {
               if (lunits == 0) {
                  off2Gg.drawString("10000",220,25);
                  off2Gg.drawString("8000",220,40);
                  off2Gg.drawString("6000",220,55);
                  off2Gg.drawString("4000",220,70);
                  off2Gg.drawString("2000",220,85);
                  scale = (95. - 20.)/ 10000. ;
                  ey[0] = 20 + (int) (scale*(10000. - mconv1*fuelrat)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("5000",220,25);
                  off2Gg.drawString("4000",220,40);
                  off2Gg.drawString("3000",220,55);
                  off2Gg.drawString("2000",220,70);
                  off2Gg.drawString("1000",220,85);
                  scale = (95. - 20.)/ 5000. ;
                  ey[0] = 20 + (int) (scale*(5000. - mconv1*fuelrat)) ;
               }
            }
            if (planetyp == 1 || planetyp ==2) {
               if (lunits == 0) {
                  off2Gg.drawString("100000",220,25);
                  off2Gg.drawString("80000",220,40);
                  off2Gg.drawString("60000",220,55);
                  off2Gg.drawString("40000",220,70);
                  off2Gg.drawString("20000",220,85);
                  scale = (95. - 20.)/ 100000. ;
                  ey[0] = 20 + (int) (scale*(100000. - mconv1*fuelrat)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("50000",220,25);
                  off2Gg.drawString("40000",220,40);
                  off2Gg.drawString("30000",220,55);
                  off2Gg.drawString("20000",220,70);
                  off2Gg.drawString("10000",220,85);
                  scale = (95. - 20.)/ 50000. ;
                  ey[0] = 20 + (int) (scale*(50000. - mconv1*fuelrat)) ;
               }
            }
            if (planetyp == 3) {
               if (lunits == 0) {
                  off2Gg.drawString("20000",220,25);
                  off2Gg.drawString("16000",220,40);
                  off2Gg.drawString("12000",220,55);
                  off2Gg.drawString("8000",220,70);
                  off2Gg.drawString("4000",220,85);
                  scale = (95. - 20.)/ 20000. ;
                  ey[0] = 20 + (int) (scale*(20000. - mconv1*fuelrat)) ;
               }
               if (lunits == 1) {
                  off2Gg.drawString("10000",220,25);
                  off2Gg.drawString("8000",220,40);
                  off2Gg.drawString("6000",220,55);
                  off2Gg.drawString("4000",220,70);
                  off2Gg.drawString("2000",220,85);
                  scale = (95. - 20.)/ 10000. ;
                  ey[0] = 20 + (int) (scale*(10000. - mconv1*fuelrat)) ;
               }
            }
            ey[1] = ey[0] - 5 ;
            ey[2] = ey[0] + 5 ;
            off2Gg.setColor(Color.yellow) ;
            off2Gg.fillRect(ex[1],ey[1]+2,18,6) ;
            off2Gg.fillPolygon(ex,ey,3) ;
            off2Gg.drawString(String.valueOf(filter0(mconv1*fuelrat)),235,115);
            off2Gg.setColor(Color.green) ;
            if(lunits == 0) off2Gg.drawString("Fuel - lb/hr",225,10);
            if(lunits == 1) off2Gg.drawString("Fuel - kg/hr",225,10);
      
            g.drawImage(offImg2,0,0,this) ;

        }
     }
  }

  class L extends Panel {
     Range outerparent ;
     Lr lr ;
     Ll ll ;

     L (Range target) { 
          outerparent = target ;
          setLayout(new GridLayout(1,2,5,5)) ;
 
          ll = new Ll(outerparent) ;
          lr = new Lr(outerparent) ;

          add(ll) ;  
          add(lr) ;  
     }

     class Ll extends Panel {
// dispay problem 
        Range outerparent ;
        TextArea prout ;
        Pr pr ;

        Ll (Range target) {

         outerparent = target ;
         setLayout(new BorderLayout(5,5)) ;
//         setLayout(new GridLayout(2,1,2,2)) ;

         prout = new TextArea() ;
         prout.setBackground(Color.white) ;
         prout.setForeground(Color.black) ;

         pr = new Pr() ;

         add("North", pr) ;  
         add("Center", prout) ;  
//         add(pr) ;  
//         add(prout) ;  

       }
    
       class Pr extends Panel {
   //  problem  container
           Range outerparent ;
           Rngprb1 rngprb1 ;
           Rngprb2 rngprb2 ;
           Watprb watprb ;
           Motprb1 motprb1 ;
           Motprb2 motprb2 ;
   
           Pr () {
   
              probin = new CardLayout() ;
              setLayout(probin) ;

              rngprb1 = new Rngprb1() ;
              rngprb2 = new Rngprb2() ;
              watprb = new Watprb() ;
              motprb1 = new Motprb1() ;
              motprb2 = new Motprb2() ;

              add ("first", rngprb1) ;
              add ("second", rngprb2) ;
              add ("third", watprb) ;
              add ("fourth", motprb1) ;
              add ("fifth", motprb2) ;
           }

           class Rngprb1 extends Panel {
              Range outerparent ;
              Choice prch ;
 
              Rngprb1 () {
                setLayout(new GridLayout(1,1,5,0)) ;

                prch = new Choice() ;
                prch.addItem("Find Distance -Level 1") ;
                prch.addItem("Find Time -Level 1");
                prch.setBackground(Color.blue) ;
                prch.setForeground(Color.white) ;
                prch.select(0) ;

                add(prch) ;  
              }

              public boolean action(Event evt, Object arg) {
                 if(evt.target instanceof Choice) {
                   String label = (String)arg ;
                   if(label.equals("Find Distance -Level 1")) {
                     rngopt = 1 ;
                     l.lr.up.anmc.l1.setText("Range") ;
                     l.lr.up.anin.l1.setText("Range") ;
                     if(lunits == 0) {
                          l.lr.up.anmc.l2.setText("miles") ;
                          l.lr.up.anin.l2.setText("miles") ;
                     }
                     if(lunits == 1) {
                          l.lr.up.anmc.l2.setText("km") ;
                          l.lr.up.anin.l2.setText("km") ;
                     }
                   }
                   if(label.equals("Find Time -Level 1")) {
                     rngopt = 2 ;
                     l.lr.up.anmc.l1.setText("Time") ;
                     l.lr.up.anin.l1.setText("Time") ;
                     l.lr.up.anmc.l2.setText("hrs") ;
                     l.lr.up.anin.l2.setText("hrs") ;
                   }
                   if (playflg != 3) trymax = 3 ;
                   l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                   antrig = 0 ;
                   counter = 0 ;
                   curcal = 1 ;
                   computeRange() ;
                   return true ;
                 }
                 else return false ;
              }  // end handler
           }  // end rngprb1

           class Rngprb2 extends Panel {
              Range outerparent ;
              Choice prch ;
 
              Rngprb2 () {
                setLayout(new GridLayout(1,1,5,0)) ;

                prch = new Choice() ;
                prch.addItem("Find Distance -Level 2") ;
                prch.setBackground(Color.blue) ;
                prch.setForeground(Color.white) ;
                prch.select(0) ;

                add(prch) ;  
              }

              public boolean action(Event evt, Object arg) {
                 if(evt.target instanceof Choice) {
                   String label = (String)arg ;
                   if(label.equals("Find Distance -Level 2")) {
                     rngopt = 3 ;
                     l.lr.up.anmc.l1.setText("Range") ;
                     l.lr.up.anin.l1.setText("Range") ;
                     if(lunits == 0) {
                          l.lr.up.anmc.l2.setText("miles") ;
                          l.lr.up.anin.l2.setText("miles") ;
                     }
                     if(lunits == 1) {
                          l.lr.up.anmc.l2.setText("km") ;
                          l.lr.up.anin.l2.setText("km") ;
                     }
                   }
                   if (playflg != 3) trymax = 3 ;
                   l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                   antrig = 0 ;
                   counter = 0 ;
                   curcal = 1 ;
                   computeRange() ;
                   return true ;
                 }
                 else return false ;
              }  // end handler
           }  // end rngprb2

           class Watprb extends Panel {
              Range outerparent ;
              Choice prch ;
 
              Watprb () {
                setLayout(new GridLayout(1,1,5,0)) ;

                prch = new Choice() ;
                prch.addItem("Find Mass -Level 1") ;
                prch.setBackground(Color.blue) ;
                prch.setForeground(Color.white) ;
                prch.select(0) ;

                add(prch) ;  
              }

              public boolean action(Event evt, Object arg) {
                 if(evt.target instanceof Choice) {
                   String label = (String)arg ;
                   if(label.equals("Find Mass -Level 1")) {
                     rngopt = 11 ;
                     l.lr.up.anmc.l1.setText("Mass") ;
                     l.lr.up.anin.l1.setText("Mass") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("slugs") ;
                         l.lr.up.anin.l2.setText("slugs") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("kg") ;
                         l.lr.up.anin.l2.setText("kg") ;
                     }
                   }
                   if (playflg != 3) trymax = 3 ;
                   l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                   antrig = 0 ;
                   counter = 0 ;
                   curcal = 1 ;
                   computeRange() ;
                   return true ;
                 }
                 else return false ;
              }  // end handler
           }  // end watprb

           class Motprb1 extends Panel {
              Range outerparent ;
              Choice prch ;
 
              Motprb1 () {
                setLayout(new GridLayout(1,1,5,0)) ;

                prch = new Choice() ;
                prch.addItem("Find Accel -Level 1") ;
                prch.addItem("Find Velocity given Time -Level 1") ;
                prch.addItem("Find Time given Velocity -Level 1") ;
                prch.addItem("Find Distance given Time -Level 2") ;
                prch.addItem("Find Time given Distance -Level 2") ;
                prch.setBackground(Color.blue) ;
                prch.setForeground(Color.white) ;
                prch.select(0) ;

                add(prch) ;  
              }

              public boolean action(Event evt, Object arg) {
                 if(evt.target instanceof Choice) {
                   String label = (String)arg ;
                   if(label.equals("Find Accel -Level 1")) {
                     rngopt = 12 ;
                     l.lr.up.anmc.l1.setText("Accel") ;
                     l.lr.up.anin.l1.setText("Accel") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec^2") ;
                         l.lr.up.anin.l2.setText("ft/sec^2") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec^2") ;
                         l.lr.up.anin.l2.setText("m/sec^2") ;
                     }
                   }
                   if(label.equals("Find Velocity given Time -Level 1")) {
                     rngopt = 13 ;
                     l.lr.up.anmc.l1.setText("Velocity") ;
                     l.lr.up.anin.l1.setText("Velocity") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec") ;
                         l.lr.up.anin.l2.setText("ft/sec") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec") ;
                         l.lr.up.anin.l2.setText("m/sec") ;
                     }
                   }
                   if(label.equals("Find Time given Velocity -Level 1")) {
                     rngopt = 14 ;
                     l.lr.up.anmc.l1.setText("Time") ;
                     l.lr.up.anin.l1.setText("Time") ;
                     l.lr.up.anmc.l2.setText("secs") ;
                     l.lr.up.anin.l2.setText("secs") ;
                   }
                   if(label.equals("Find Distance given Time -Level 2")) {
                     rngopt = 15 ;
                     l.lr.up.anmc.l1.setText("Distance") ;
                     l.lr.up.anin.l1.setText("Distance") ;
                     if(lunits ==0) {
                         l.lr.up.anmc.l2.setText("feet") ;
                         l.lr.up.anin.l2.setText("feet") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("meters") ;
                         l.lr.up.anin.l2.setText("meters") ;
                     }
                   }
                   if(label.equals("Find Time given Distance -Level 2")) {
                     rngopt = 16 ;
                     l.lr.up.anmc.l1.setText("Time") ;
                     l.lr.up.anin.l1.setText("Time") ;
                     l.lr.up.anmc.l2.setText("secs") ;
                     l.lr.up.anin.l2.setText("secs") ;
                   }
                   if (playflg != 3) trymax = 3 ;
                   l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                   antrig = 0 ;
                   counter = 0 ;
                   curcal = 1 ;
                   computeRange() ;
                   return true ;
                 }
                 else return false ;
              }  // end handler
           }  // end motprb1

           class Motprb2 extends Panel {
              Range outerparent ;
              Choice prch ;
 
              Motprb2 () {
                setLayout(new GridLayout(1,1,5,0)) ;

                prch = new Choice() ;
                prch.addItem("Find Accel -Level 2") ;
                prch.addItem("Find Velocity given Time -Level 2") ;
                prch.addItem("Find Time given Velocity -Level 2") ;
                prch.addItem("Find Distance given Time -Level 3") ;
                prch.addItem("Find Time given Distance -Level 3") ;
                prch.addItem("Find Velocity given Distance -Level 4") ;
                prch.addItem("Find Distance given Velocity -Level 4") ;
                prch.setBackground(Color.blue) ;
                prch.setForeground(Color.white) ;
                prch.select(0) ;

                add(prch) ;  
              }

              public boolean action(Event evt, Object arg) {
                 if(evt.target instanceof Choice) {
                   String label = (String)arg ;
                   if(label.equals("Find Accel -Level 2")) {
                     rngopt = 12 ;
                     l.lr.up.anmc.l1.setText("Accel") ;
                     l.lr.up.anin.l1.setText("Accel") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec^2") ;
                         l.lr.up.anin.l2.setText("ft/sec^2") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec^2") ;
                         l.lr.up.anin.l2.setText("m/sec^2") ;
                     }
                   }
                   if(label.equals("Find Velocity given Time -Level 2")) {
                     rngopt = 13 ;
                     l.lr.up.anmc.l1.setText("Velocity") ;
                     l.lr.up.anin.l1.setText("Velocity") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec") ;
                         l.lr.up.anin.l2.setText("ft/sec") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec") ;
                         l.lr.up.anin.l2.setText("m/sec") ;
                     }
                   }
                   if(label.equals("Find Time given Velocity -Level 2")) {
                     rngopt = 14 ;
                     l.lr.up.anmc.l1.setText("Time") ;
                     l.lr.up.anin.l1.setText("Time") ;
                     l.lr.up.anmc.l2.setText("secs") ;
                     l.lr.up.anin.l2.setText("secs") ;
                   }
                   if(label.equals("Find Distance given Time -Level 3")) {
                     rngopt = 15 ;
                     l.lr.up.anmc.l1.setText("Distance") ;
                     l.lr.up.anin.l1.setText("Distance") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("feet") ;
                         l.lr.up.anin.l2.setText("feet") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("meters") ;
                         l.lr.up.anin.l2.setText("meters") ;
                     }
                   }
                   if(label.equals("Find Time given Distance -Level 3")) {
                     rngopt = 16 ;
                     l.lr.up.anmc.l1.setText("Time") ;
                     l.lr.up.anin.l1.setText("Time") ;
                     l.lr.up.anmc.l2.setText("secs") ;
                     l.lr.up.anin.l2.setText("secs") ;
                   }
                   if(label.equals("Find Velocity given Distance -Level 4")) {
                     rngopt = 17 ;
                     l.lr.up.anmc.l1.setText("Velocity") ;
                     l.lr.up.anin.l1.setText("Velocity") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("ft/sec") ;
                         l.lr.up.anin.l2.setText("ft/sec") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("m/sec") ;
                         l.lr.up.anin.l2.setText("m/sec") ;
                     }
                   }
                   if(label.equals("Find Distance given Velocity -Level 4")) {
                     rngopt = 18 ;
                     l.lr.up.anmc.l1.setText("Distance") ;
                     l.lr.up.anin.l1.setText("Distance") ;
                     if(lunits == 0) {
                         l.lr.up.anmc.l2.setText("feet") ;
                         l.lr.up.anin.l2.setText("feet") ;
                     }
                     if(lunits == 1) {
                         l.lr.up.anmc.l2.setText("meters") ;
                         l.lr.up.anin.l2.setText("meters") ;
                     }
                   }
                   if (playflg != 3) trymax = 3 ;
                   l.lr.dwn.d3.remain.setText(String.valueOf(trymax));
                   antrig = 0 ;
                   counter = 0 ;
                   curcal = 1 ;
                   computeRange() ;
                   return true ;
                 }
                 else return false ;
              }  // end handler
           }  // end motprb1
        }  // end probcon
     } // end loleft

     class Lr extends Panel {
// dispay  and answers
        Range outerparent ;
        Uppnl up ;
        Dwnpnl dwn ;

        Lr (Range target) {

         outerparent = target ;
         setLayout(new GridLayout(2,1,5,0)) ;

         up = new Uppnl() ;
         dwn = new Dwnpnl() ;

         add(up) ;  
         add(dwn) ;  

       }

       class Uppnl extends Panel {
           Anmc anmc ;
           Anin anin ;

           Uppnl () {
              layout = new CardLayout() ;
              setLayout(layout) ;
 
              anmc = new Anmc() ;
              anin = new Anin() ;

              add ("first", anmc) ;
              add ("second", anin) ;
           }

           class Anmc extends Panel {
               Label l1,l2;
               Label lans;
               Checkbox ch1,ch2,ch3,ch4 ;
               CheckboxGroup cbg ;
               Label sco1,sco2,sco3,sco4;
               Label lch1,lch2,lch3,lch4;
    
               Anmc () {
                  setLayout(new GridLayout(3,6,0,0)) ;
                  cbg = new CheckboxGroup() ;
 
                  ch1 = new Checkbox("",cbg,true) ;
                  lch1 = new Label("ans1", Label.LEFT) ;
                  ch2 = new Checkbox("",cbg,false) ;
                  lch2 = new Label("ans1", Label.LEFT) ;
                  ch3 = new Checkbox("",cbg,false) ;
                  lch3 = new Label("ans1", Label.LEFT) ;
                  ch4 = new Checkbox("",cbg,false) ;
                  lch4 = new Label("ans1", Label.LEFT) ;

                  lans = new Label("Answer", Label.LEFT) ;
                  lans.setBackground(Color.white) ;
                  lans.setForeground(Color.red) ;

                  l1 = new Label("Range", Label.CENTER) ;
                  l2 = new Label(" miles", Label.CENTER) ;

                  sco1 = new Label("X", Label.CENTER) ;
                  sco1.setForeground(Color.red) ;
                  sco2 = new Label(" ", Label.CENTER) ;
                  sco3 = new Label("--->", Label.CENTER) ;
                  sco3.setForeground(Color.blue) ;
                  sco4 = new Label(" ", Label.CENTER) ;

                  add(lans) ;
                  add(new Label("Find",Label.CENTER)) ;
                  add(l1) ;  
                  add(new Label("in ",Label.CENTER)) ;
                  add(l2) ;  
                  add(new Label("..Submit",Label.CENTER)) ;
     
                  add(sco1) ; 
                  add(ch1) ; 
                  add(lch1) ; 
                  add(sco2) ; 
                  add(ch2) ;
                  add(lch2) ; 
    
                  add(sco3) ; 
                  add(ch3) ;
                  add(lch3) ; 
                  add(sco4) ; 
                  add(ch4) ;
                  add(lch4) ; 
               }
           } // end anmc

           class Anin extends Panel {
               Label l1,l2;
               Label lans;
               TextField inp1 ;
    
               Anin () {
                  setLayout(new GridLayout(3,3,0,0)) ;

                  lans = new Label("Answer", Label.LEFT) ;
                  lans.setBackground(Color.white) ;
                  lans.setForeground(Color.red) ;

                  l1 = new Label("Range", Label.CENTER) ;
                  l2 = new Label(" miles", Label.CENTER) ;

                  inp1 = new TextField("Input") ;
                  inp1.setBackground(Color.white) ;
                  inp1.setForeground(Color.black) ;
    
                  add(lans) ;
                  add(new Label("Compute the ",Label.CENTER)) ;
                  add(l1) ;  

                  add(new Label("in",Label.CENTER)) ;
                  add(l2) ;  
                  add(new Label(" ",Label.CENTER)) ;

                  add(new Label("Enter here->",Label.CENTER)) ;
                  add(inp1) ;  
                  add(new Label(".. Submit",Label.CENTER)) ;
               }
            } // end anin
        }  // end uppnl
    
        class Dwnpnl extends Panel {
           D1 d1 ;
           D2 d2 ;
           D3 d3 ;

           Dwnpnl () {
              setLayout(new GridLayout(3,1,10,0)) ;
 
              d1 = new D1() ;
              d2 = new D2() ;
              d3 = new D3() ;

              add(d1) ;  
              add(d2) ;  
              add(d3) ;  
           }

           class D1 extends Panel {
              Button submit ;
 
              D1 () {
                setLayout(new GridLayout(1,1,50,0)) ;
                submit = new Button("Submit Answer") ;
                submit.setBackground(Color.red) ;
                submit.setForeground(Color.white) ;

                add(submit) ;  
              }

              public Insets insets() {
                 return new Insets(0,50,0,50) ;
              }

              public boolean action(Event evt, Object arg) {
                 Double V1 ;
                 double v1 ;

                 if(evt.target instanceof Button) {
                   String label = (String)arg ;
                   if(label.equals("Submit Answer")) {
                     inflag = 1 ;
                     if (trys == 0) {
                          rngcal = 0 ;
                          return true ;
                     }
                     rngcal = rngopt ;
                     if (prmode == 1) {
                       V1 = Double.valueOf(l.lr.up.anin.inp1.getText()) ;
                       v1 = V1.doubleValue() ;
                       if(rngopt == 2) timg = v1  ;
                       if(rngopt == 1  || rngopt == 3) rngg = v1/lconv2 ;
                       if(rngopt == 11) masg = v1/mconv2 ;
                       if(rngopt == 12) aclg = v1/lconv1  ;
                       if(rngopt == 13 || rngopt == 17) velg = v1 ;
                       if(rngopt == 14 || rngopt == 16) timg = v1 ;
                       if(rngopt == 15 || rngopt == 18) disg = v1 ;
                     }
                   }
                   computeRange () ;
                   return true ;
                 }
                 else return false ;
              }
           }

           class D2 extends Panel {
              TextField stat ;

              D2 () {
                setLayout(new GridLayout(1,1,5,5)) ;
                stat = new TextField("Use the Blue Buttons to select problem.");
                stat.setBackground(Color.red) ;
                stat.setForeground(Color.white) ;

                add(stat) ;  
              }
           }

           class D3 extends Panel {
              Label l1,l2,l3;
              TextField rite,rong,remain ;

              D3 () {
                setLayout(new GridLayout(1,6,5,5)) ;

                l1 = new Label("Right", Label.CENTER) ;
                rite = new TextField("0") ;
                l2 = new Label("Wrong", Label.CENTER) ;
                rong = new TextField("0") ;
                l3 = new Label("Tries", Label.CENTER) ;
                remain = new TextField("3") ;
                remain.setForeground(Color.blue) ;

                add(l1) ;  
                add(rite) ;  
                add(l2) ;  
                add(rong) ;  
                add(l3) ;  
                add(remain) ;  
              }
           }
        }
     }
  }

  class Viewer extends Canvas 
         implements Runnable{
     Range outerparent ;
     Thread runner ;
     Image displimg ;

     Viewer (Range target) {
         setBackground(Color.black) ;
         runner = null ;
         displimg = getImage(getCodeBase(),"exec.gif") ;
     }

     public Insets insets() {
        return new Insets(0,10,5,10) ;
     }

     public void start() {
        if (runner == null) {
           runner = new Thread(this) ;
           runner.start() ;
        }
        counter = 0 ;
     }
 
     public void run() {
       while (true) {
          counter ++ ;
          if (antrig == 0) {
           if(entype == 0) displimg = aneximg[counter-1] ;
           if(entype == 1) displimg = anfiimg[counter-1] ;
           if(entype == 2) displimg = anlnimg[counter-1] ;
           if(entype == 3) displimg = anhyimg[counter-1] ;
          }
          if (antrig == 1) {
           if(entype == 0) displimg = exdimg[counter-1] ;
           if(entype == 1) displimg = fidimg[counter-1] ;
           if(entype == 2) displimg = lndimg[counter-1] ;
           if(entype == 3) displimg = hydimg[counter-1] ;
          }
          if (antrig == 2) {
           if(entype == 0) displimg = exrimg[counter-1] ;
           if(entype == 1) displimg = firimg[counter-1] ;
           if(entype == 2) displimg = lnrimg[counter-1] ;
           if(entype == 3) displimg = hyrimg[counter-1] ;
          }
          if (antrig == 3) {
           if(entype == 0) displimg = execimg ;
           if(entype == 1) displimg = fiteimg ;
           if(entype == 2) displimg = linrimg ;
           if(entype == 3) displimg = hyprimg ;
          }
          try { Thread.sleep(500); }
          catch (InterruptedException e) {}
          view.repaint() ;
          if (antrig == 0 && counter == 4) counter = 0 ;
          if (antrig == 1 && counter == 9) {
              counter = 0 ;
              antrig = 0 ; 
          }
          if (antrig == 2 && counter == 9) {
              counter = 0 ;
              antrig = 0 ; 
          }
       }
     }

     public void update(Graphics g) {
        view.paint(g) ;
     }
 
     public void paint(Graphics g) {
        offsGg.drawImage(displimg,0,0,600,110,this) ;
        g.drawImage(offscreenImg,0,0,this) ;   
     }
   }

   class Printo extends Panel {
     TextArea prnt ;

     Printo () { 

        setLayout(new GridLayout(1,1,0,0)) ;

        prnt = new TextArea() ;
        prnt.setEditable(false) ;

        prnt.appendText("RangeGames 1.3 beta - 18 Sep 00  --  NASA Glenn  Learning Technologies Project\n") ;
        add(prnt) ;  
     }

   }
}
