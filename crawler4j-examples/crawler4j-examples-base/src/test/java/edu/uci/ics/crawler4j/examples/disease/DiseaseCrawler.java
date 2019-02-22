/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.examples.disease;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.Header;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Yasser Ganjisaffar
 */
public class DiseaseCrawler extends WebCrawler {

    private static final Pattern DISEASE_PAGE = Pattern.compile("^(https://www.baikemy.com/disease/)(list/(3|5)/0.*|view.*|detail/(\\d+)/1)");
    private static final List<String> VISIT_LIST = Arrays.asList(
        "https://www.baikemy.com/disease/list/3/0", // 妇产科
        "https://www.baikemy.com/disease/list/5/0", // 儿科
        "https://www.baikemy.com/disease/detail", // 详情页标签页
        "https://www.baikemy.com/disease/view" // 详情首页
    );
    /**
     * You should implement this function to specify whether the given webURL
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL webURL) {
        String href = webURL.getURL().toLowerCase();
        return DISEASE_PAGE.matcher(href).matches();
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();

        // todo if url contains list 获取科室下疾病信息
        // todo if url contains detail 获取疾病的详细信息
        // todo if url contains view 忽略
        logger.debug("Docid: {}", docid);
        logger.info("URL: {}", url);
        logger.debug("Domain: '{}'", domain);
        logger.debug("Sub-domain: '{}'", subDomain);
        logger.debug("Path: '{}'", path);
        logger.debug("Parent page: {}", parentUrl);
        logger.debug("Anchor text: {}", anchor);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();

            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            logger.debug("Text length: {}", text.length());
            logger.debug("Html length: {}", html.length());
            logger.debug("Number of outgoing links: {}", links.size());
        }

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            logger.debug("Response headers:");
            for (Header header : responseHeaders) {
                logger.debug("\t{}: {}", header.getName(), header.getValue());
            }
        }

        logger.debug("=============");
    }

    public static void main(String[] args) {
        String url_patten = "^(https://www.baikemy.com/disease/)(list/(3|5)/0.*|view.*|detail/(\\d+)/1)";
        Pattern IMAGE_EXTENSIONS = Pattern.compile(url_patten);
        String href = "https://www.baikemy.com/disease/view/34867459183873";
        System.out.println(IMAGE_EXTENSIONS.matcher(href).matches());
    }
}
