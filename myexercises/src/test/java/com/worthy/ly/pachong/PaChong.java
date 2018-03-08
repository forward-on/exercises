package com.worthy.ly.pachong;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ly on 2018/2/6.
 */
@Component
public class PaChong extends BreadthCrawler {

    public PaChong() {
        super("crawl", true);
    /*start page*/
        this.addSeed("xxxx");
    /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
        this.addRegex("xxx");
    /*do not fetch jpg|png|gif*/
        this.addRegex("-.*\\.(jpg|png|gif).*");
    /*do not fetch url contains #*/
    //        this.addRegex("-.*#.*");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        /*String url = page.getUrl();
        String content = "";
        try {
            content = ContentExtractor.getContentByUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
      *//*抽取标题*//*
        String title = page.getDoc().title();
        System.out.println("-------------------->" + title);*/
//        if (!title.isEmpty() && !content.isEmpty()) {
//            Pa pa = new Pa(title, content);
//            ipaDao.save(pa);//持久化到数据库
//        }
    }
}
