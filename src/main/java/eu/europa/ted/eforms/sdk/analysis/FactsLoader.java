package eu.europa.ted.eforms.sdk.analysis;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import eu.europa.ted.eforms.sdk.analysis.fact.CodelistFact;
import eu.europa.ted.eforms.sdk.analysis.fact.DocumentTypeFact;
import eu.europa.ted.eforms.sdk.analysis.fact.FieldFact;
import eu.europa.ted.eforms.sdk.analysis.fact.LabelFact;
import eu.europa.ted.eforms.sdk.analysis.fact.NodeFact;
import eu.europa.ted.eforms.sdk.analysis.fact.NoticeTypeFact;
import eu.europa.ted.eforms.sdk.analysis.fact.NoticeTypesIndexFact;
import eu.europa.ted.eforms.sdk.analysis.fact.ViewTemplateFact;
import eu.europa.ted.eforms.sdk.analysis.fact.XmlNoticeFact;
import eu.europa.ted.eforms.sdk.domain.Codelist;
import eu.europa.ted.eforms.sdk.domain.Label;
import eu.europa.ted.eforms.sdk.domain.XmlNotice;
import eu.europa.ted.eforms.sdk.domain.field.Field;
import eu.europa.ted.eforms.sdk.domain.field.XmlStructureNode;
import eu.europa.ted.eforms.sdk.domain.noticetype.DocumentType;
import eu.europa.ted.eforms.sdk.domain.noticetype.NoticeType;
import eu.europa.ted.eforms.sdk.domain.view.index.TedefoViewTemplateIndex;
import eu.europa.ted.eforms.sdk.domain.view.index.TedefoViewTemplatesIndex;

public class FactsLoader {
  private static final Logger logger = LoggerFactory.getLogger(FactsLoader.class);

  private SdkLoader sdkLoader;

  public FactsLoader() {
    this(null);
  }

  public FactsLoader(Path sdkRoot) {
    sdkLoader = new SdkLoader(sdkRoot);
  }

  public DataStore<FieldFact> loadFields() throws IOException {
    logger.debug("Creating facts datastore for fields");

    final DataStore<FieldFact> datastore = DataSource.createStore();

    sdkLoader.getFieldsAndNodes().getFields()
        .forEach((Field field) -> datastore.add(new FieldFact(field)));

    return datastore;
  }

  public DataStore<NodeFact> loadNodes() throws IOException {
    logger.debug("Creating facts datastore for nodes");

    final DataStore<NodeFact> datastore = DataSource.createStore();
    List<XmlStructureNode> nodes = sdkLoader.getFieldsAndNodes().getNodes();

    nodes.forEach((XmlStructureNode node) -> datastore.add(new NodeFact(node)));

    return datastore;
  }

  public DataStore<NoticeTypeFact> loadNoticeTypes() throws IOException {
    logger.debug("Creating facts datastore for notice types");

    final DataStore<NoticeTypeFact> datastore = DataSource.createStore();
    sdkLoader.getNoticeTypes()
        .forEach((NoticeType noticeType) -> datastore.add(new NoticeTypeFact(noticeType)));

    return datastore;
  }

  public DataStore<NoticeTypesIndexFact> loadNoticeTypesIndex() throws IOException {
    logger.debug("Creating facts datastore for notice types index");

    final DataStore<NoticeTypesIndexFact> datastore = DataSource.createStore();
    datastore.add(new NoticeTypesIndexFact(sdkLoader.getNoticeTypesForIndex()));

    return datastore;
  }

  public DataStore<LabelFact> loadLabels()
      throws IOException, JAXBException, SAXException, ParserConfigurationException {
    logger.debug("Creating facts datastore for labels");

    final DataStore<LabelFact> datastore = DataSource.createStore();

    sdkLoader.getLabels()
        .forEach((Label label) -> datastore.add(new LabelFact(label)));

    return datastore;
  }

  public DataStore<ViewTemplateFact> loadViewTemplates() throws IOException {
    logger.debug("Creating facts datastore for view templates");

    TedefoViewTemplatesIndex viewTemplatesIndex = sdkLoader.getViewTemplatesIndex();

    final DataStore<ViewTemplateFact> datastore = DataSource.createStore();
    viewTemplatesIndex.getViewTemplates()
        .forEach((TedefoViewTemplateIndex viewTemplate) -> datastore
            .add(new ViewTemplateFact(viewTemplate)));

    return datastore;
  }

  public DataStore<DocumentTypeFact> loadDocumentTypes() throws IOException {
    logger.debug("Creating facts datastore for document types");

    final DataStore<DocumentTypeFact> datastore = DataSource.createStore();
    sdkLoader.getNoticeTypesForIndex().getDocumentTypes()
        .forEach((DocumentType documentType) -> datastore
            .add(new DocumentTypeFact(documentType)));

    return datastore;
  }

  public DataStore<CodelistFact> loadCodelists()
      throws IOException, JAXBException, SAXException, ParserConfigurationException {
    logger.debug("Creating facts datastore for codelists");

    final DataStore<CodelistFact> datastore = DataSource.createStore();
    sdkLoader.getCodelists()
        .forEach((Codelist codelist) -> datastore.add(new CodelistFact(codelist)));

    return datastore;
  }

  public DataStore<XmlNoticeFact> loadXmlNotices()
      throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
    logger.debug("Creating facts datastore for XML notice examples");

    final DataStore<XmlNoticeFact> datastore = DataSource.createStore();
    sdkLoader.getXmlNotices()
        .forEach((XmlNotice xmlNotice) -> datastore
            .add(new XmlNoticeFact(xmlNotice)));

    return datastore;
  }
}
