package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = rootElement.selectNodes("//select");
        if(list.size()>0) {
            parseSqlXml(list, namespace);
        }

        List<Element> insertList = rootElement.selectNodes("//insert");
        if(insertList.size()>0) {
            parseSqlXml(insertList, namespace);
        }

        List<Element> updateList = rootElement.selectNodes("//update");
        if(updateList.size()>0) {
            parseSqlXml(updateList, namespace);
        }

        List<Element> deleteList = rootElement.selectNodes("//delete");
        if(deleteList.size()>0) {
            parseSqlXml(deleteList, namespace);
        }

    }

    private void parseSqlXml(List<Element> list,String namespace){
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);

        }
    }

}
