package com.example.withplaceprototype.definition;

public interface ResultCode {
    String  MATCH_CODE_SUCCESS = "M000",
            MATCH_CODE_UNKNOWN_PATH = "M400",
            MATCH_CODE_NOT_SUPPORTED_METHOD = "M401",
            MATCH_CODE_NOT_SUPPORTED_CONTENT_TYPE ="M402",
            MATCH_CODE_JSON_PARSER_ERROR = "M403",
            MATCH_CODE_USER_ID_INVALID = "M500",
            MATCH_CODE_DUPLICATE_REQUEST = "M501",
            MATCH_CODE_INTERNAL_SERVER_ERROR = "M999";
}
