package com.example.mydemoapp;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class HtmlUnitDemoTest {

    private WebClient webClient;

    @BeforeEach
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
    }

    @AfterEach
    public void tearDown() {
        webClient.close();
    }

    @Test
    void testPageContent() throws Exception {
        HtmlPage page = webClient.getPage("http://localhost:8082/index.html");

        // Check the page title
        assertThat(page.getTitleText()).isEqualTo("HTMLUnit Demo");

        // Check paragraph text
        assertThat(page.getElementById("message").asNormalizedText()).isEqualTo("Welcome to the HTMLUnit Demo Page!");
    }

    @Test
    public void testFormSubmission() throws Exception {
        HtmlPage page = webClient.getPage("http://localhost:8080/index.html");

        // Find the form and fill out the input
        HtmlForm form = page.getHtmlElementById("sampleForm");
        HtmlTextInput nameInput = form.getInputByName("name");
        nameInput.setValueAttribute("John Doe");

        // Submit the form
        HtmlSubmitInput submitButton = form.getOneHtmlElementByAttribute("button", "type", "submit");
        HtmlPage resultPage = submitButton.click();

        // Verify the result of the form submission
        assertThat(resultPage.asNormalizedText()).contains("Form submitted successfully");
    }

    @Test
    public void testSampleLink() throws Exception {
        HtmlPage page = webClient.getPage("http://localhost:8080/index.html");

        // Click on the link
        HtmlAnchor link = page.getHtmlElementById("sampleLink");
        HtmlPage linkedPage = link.click();

        // Verify navigation to linked page
        assertThat(linkedPage.getUrl().toString()).endsWith("/sample-link");
    }
}

