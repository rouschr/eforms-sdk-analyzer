package eu.europa.ted.eforms.sdk.domain.field;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TEDEFO-546: Implements a codelist constraint item.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BooleanConstraint extends AbstractConstraint<Boolean> {
  private static final long serialVersionUID = 9027962446099162029L;
}
