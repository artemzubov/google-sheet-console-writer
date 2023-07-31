package com.zubov.ident.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FilesToClean {

  private String googleDocId;
  private String googleDocPageId;
}
