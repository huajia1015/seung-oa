package com.oa.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
* 一个校验码生成器的类，其功能主要包括两部分(得到渲染图片和校验码字符串)
* 同时可以设置图片中字符的字体大小及字符的个数(图片的大小会根据所设置的相关参数而自适应)
*/
public class ValidateCodeGenerator {

    private int charSize = 24; //默认字符的字体大小
    private Font font;

    private int charsAmount = 5; //默认产生字符的数量
    private int imageWidth;  //产生出的图像宽度
    private int imageHeight; //产生出的图像高度

    private int unitCharPixel;
    private int unitHeight;

    private String validateCode = "";

    /**
     * 默认构造函数
     */
    public ValidateCodeGenerator() {
        
    }

    /**
     * 带参数的构造函数(需要传入字体大小及字符总数)
     * @param {int} charSize
     * @param {int} charsAmount
     */
    public ValidateCodeGenerator(int charSize, int charsAmount){
        setCharSize(charSize);
        setCharTotal(charsAmount);
    }
    

    /**
     * 设置字符的字体大小
     * @param {int} charSize
     */
    public void setCharSize(int charSize) {
        if(charSize<20){
            charSize=20; //最小需为20
        }
        //根据情况也可设置最大字体限制
        this.charSize = charSize;
    }

    /**
     * 设置字符的总数
     * @param {int} charsAmount
     */
    public void setCharTotal(int charsAmount) {
//        if(charsAmount<4){
//            charsAmount=4; //最少需要4个字符
//        }
//        if(charsAmount>12){
//            charsAmount=12; //最多允许12个字符
//        }
        this.charsAmount = charsAmount;
    }

    /**
     * 得到一个渲染图像(并且检验码字符串在此过程产生)
     * @return {RenderedImage}
     */
    private RenderedImage generateImage() {
        init(charSize, charsAmount); //生成图片之前进行参数初始化
        
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        Random random = new Random();
        graphics.setColor(getRandomColor(200, 250)); // 将图形上下文(填充区域)的当前颜色设置为指定颜色
        graphics.fillRect(0, 0, imageWidth - 1, imageHeight - 1); // 设置填充区域
        graphics.setColor(new Color(102, 102, 102)); // 将图形上下文(绘画区域边框)的当前颜色设置为指定颜色
        graphics.drawRect(0, 0, imageWidth - 1, imageHeight - 1); // 设置绘画区域边框
        graphics.setFont(font); // 设置字体
        graphics.setColor(getRandomColor(160, 200)); // 将图形上下文(区域内线条)的当前颜色设置为指定颜色

        int lineCount = charsAmount * 75;  //需要画的线条数
        for (int i = 0; i < lineCount; i++) {
            //为图像画线条
            int x = random.nextInt(imageWidth - 1);
            int y = random.nextInt(imageHeight - 1);
            int xl = 0, yl = 0;
            if (i % 2 == 0) {
                xl = random.nextInt(6) + 1;
                yl = random.nextInt(12) + 1;
            } else {
                xl = -(random.nextInt(12) + 1);
                yl = -(random.nextInt(6) + 1);
            }
            graphics.drawLine(x, y, x + xl, y + yl);
        }

        for (int i = 0; i < charsAmount; i++) {
            String sChar = getRandomChar();
            validateCode += sChar; // 得到charsCount随机字符形成一个串
            //为每一个字符设置独立的前景色
            graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            graphics.drawString(sChar, unitCharPixel * i + charsAmount / 2, unitHeight);
        }

        graphics.dispose();
        return image;
    }

    /**
     * 得到校验码(注意：若想取得正确的校验码,需要先生成图像(即调用getImage()方法))
     * @see ValidateCodeGenerator#getImage()
     * @return {String}
     */
    public String getCode() {
        return validateCode;
    }
    
    /**
     * 得到图片
     * @return {RenderedImage}
     */
    public RenderedImage getImage(){
        return generateImage();
    }

    /**
     * 根据字符大小和字符总数进行相关参数的初始化
     * @param charSize
     * @param charsAmount
     */
    private void init(int charSize, int charsAmount) {
        font = new Font("Arial Black", Font.PLAIN, charSize);
        imageWidth = charsAmount * (int) (charSize * 0.9);
        imageHeight = (int) (Math.ceil(charSize / 0.8));
        unitCharPixel = (imageWidth - charsAmount) / charsAmount;
        unitHeight = (imageHeight - charSize) / 2 + charSize - 2;
    }
    
    /**
     * 得到随机颜色
     * @param {int} foregroundColor --前景色
     * @param {int} backgroundColor --背景色
     * @return {Color}
     */
    private Color getRandomColor(int foregroundColor, int backgroundColor) {
        Random random = new Random();

        if (foregroundColor > 255) {
            foregroundColor = 255;
        }

        if (backgroundColor > 255) {
            backgroundColor = 255;
        }

        int red = foregroundColor + random.nextInt(backgroundColor - foregroundColor);
        int green = foregroundColor + random.nextInt(backgroundColor - foregroundColor);
        int blue = foregroundColor + random.nextInt(backgroundColor - foregroundColor);

        return new Color(red, green, blue);
    }

    /**
     * 得到一个随机字符(数字)
     * @return {String}
     */
    private String getRandomChar() {
        long itmp = 0;
        itmp = Math.round(Math.random() * 9);
        return String.valueOf(itmp);
        
    }
}