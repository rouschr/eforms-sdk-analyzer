package eu.europa.ted.eforms.sdk.domain.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TEDEFO-546: Implements a codelist constraint item.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CodeListConstraint extends AbstractConstraint<CodeListPropertyValue> {
  private static final long serialVersionUID = 1275186381902387463L;
}
