package eu.europa.ted.eforms.sdk.domain.field;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * While this is called Xml... it is used to create a json object inside the fields.json. TEDEFO-552
 * xml structure node for addition in the SDK fields.json file. This represents something like a
 * field section or group of sections. See TEDEFO-555 for DB representation.
 * <p>
 * NOTE: Thiemo of enotices2 initially called this a group, but we renamed it to node.
 * </p>
 */
@Data
public class XmlStructureNode implements Serializable {
  private static final long serialVersionUID = 8566153444257534770L;

  private String id;

  /**
   * Is null if there is no parent.
   */
  private String parentId;

  private XmlStructureNode parent;

  /**
   * Human readable name.
   */
  private String name;

  /**
   * Looks like field context. See TEDEFO-555 for details.
   */
  private String xpathAbsolute;
  private String xpathRelative;

  private List<XmlElementPosition> xsdSequenceOrder;

  private boolean repeatable;

  /**
   * This was initially inferred from the parent/child relationship of node and field. A repeatable
   * parent having a field of type id, the parent indicates which field holds the identifier.
   */
  private String identifierFieldId;

  private String captionFieldId;

  /**
   * Since TEDEFO-1090. TEDEFO-1111. Can be null.
   */
  private ChangeableOrCpProperty inChangeNotice;

  /**
   * Since TEDEFO-1090. TEDEFO-1111. Can be null.
   */
  private ChangeableOrCpProperty inContinueProcedure;

  public String getId() {
    return id;
  }

  public String getParentId() {
    return parentId;
  }

  public XmlStructureNode getParent() {
    return parent;
  }

  public void setParent(XmlStructureNode parent) {
    this.parent = parent;
  }

  public boolean isRepeatable() {
    return repeatable;
  }
}
