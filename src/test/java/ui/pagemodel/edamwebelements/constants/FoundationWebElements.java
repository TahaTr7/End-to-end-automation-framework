package ui.pagemodel.edamwebelements.constants;

/**
 * Foundation web elements xtype defined.
 */
public final class FoundationWebElements {
    /**
     * Foundation Constructor Method.
     */
    private FoundationWebElements() {
        //EMPTY
    }

    /**
     * Create Button XPATH.
     * Foundation ActionBar
     */
    public static final String FOUNDATION_ACTIONBAR_CREATE_BUTTON_XPATH
            = "//betty-titlebar-secondary//button[@type='button']//coral-button-label[contains(text(),'Create')]";
    /**
     * Create Folder Option XPath.
     */
    public static final String A_ID_DAM_CREATE_FOLDER = "//a[@id='dam-create-folder']";
    /**
     * Upload File Option XPath.
     */
    public static final String A_DAM_FILEUPLOAD_SELECT = "//a[@dam-fileupload-select]";

    /**
     * Foundation Panel Header Folder Option.
     */
    public static final String FOUNDATIONAL_PANEL_HEADER_CREATE_FOLDER_OPTION = "folder";
    /**
     * Foundation Panel Header Files Option.
     */
    public static final String FOUNDATIONAL_PANEL_HEADER_CREATE_FILES_OPTION = "files";
    /**
     * Create Folder Form Title Input FieldX Path.
     */
    public static final String INPUT_NAME_JCR_CONTENT_JCR_TITLE = "//input[@name=\"./jcr:content/jcr:title\"]";
    /**
     * Create Folder Form Name Input Field XPath.
     */
    public static final String INPUT_NAME_NAME = "//input[@name=\":name\"]";
    /**
     * Create Folder Form Create Button XPath.
     */
    public static final String ID_CREATEFOLDER_BUTTON_CREATE
            = "//*[@id=\"createfolder\"]//*[@variant=\"primary\"]";

    /**
     * Create Folder Form Cancel Button XPath.
     */
    public static final String ID_CREATEFOLDER_BUTTON_CANCEL
            = "//*[@id=\"createfolder\"]//*[@variant=\"secondary\"]";

    /**
     * Brand Folder Anchor Link.
     */
    public static final String ANCHOR_BRAND_FOLDER = "//*[@id=\"{}-anchor\"]";

    /**
     * WEST ELM Anchor Link.
     */
    public static final String ANCHOR_WE = "//*[@id=\"west-elm-anchor\"]";

    /**
     * WEST ELM Anchor Link.
     */
    public static final String ANCHOR_PB = "//*[@id=\"pottery-barn-anchor\"]";

    /**
     * WEST ELM Anchor Link.
     */
    public static final String ANCHOR_WS = "//*[@id=\"williams-sonoma-anchor\"]";

    /**
     * WEST ELM Anchor Link.
     */
    public static final String ANCHOR_RJ = "//*[@id=\"rejuvenation-anchor\"]";

    /**
     * WEST ELM Anchor Link.
     */
    public static final String ANCHOR_MG = "//*[@id=\"mark-and-graham-anchor\"]";

    /**
     * Breadcrumb label text.
     */
    public static final String BREADCRUMB_LABEL_TEXT = "//div//button/coral-button-label/span/span";
}
