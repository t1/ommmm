package com.github.t1.webresource;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jboss.resteasy.client.*;
import org.junit.Before;


// @RunWith(Arquillian.class)
public class TagIT {
    static {
        System.out.println("--------------------------------------");
        System.out.flush();
        System.err.println("--------------------------------------");
        System.err.flush();
    }

    private static final String MODULE = "tag-it";
    private static final String BASE_URL = "http://localhost:8080/" + MODULE + "/";

    // private static final String XML_HEADER = PersonWebResourceIT.XML_HEADER;
    // private static final String XML = XML_HEADER + "\n" //
    // + "<tag key=\"X\">hiho</tag>\n";
    //
    // @Deployment
    // public static WebArchive createDeployment() {
    // return ShrinkWrap.create(WebArchive.class, MODULE + ".war") //
    // .addClasses(Tag.class, TagWebResource.class) //
    // ;
    // }

    private final HttpClient http = new DefaultHttpClient();

    private String tagsBefore;
    private Long created;

    @Before
    public void before() throws Exception {
        tagsBefore = getTags();
        System.out.println(tagsBefore);
    }

    private String getTags() throws Exception {
        ClientRequest request = new ClientRequest(BASE_URL);
        ClientResponse<String> response = request.get(String.class);
        return response.getEntity();
    }

    // private String getEntity(HttpResponse response) throws IOException {
    // HttpEntity entity = response.getEntity();
    // if (entity == null)
    // return null;
    // try (InputStream input = entity.getContent()) {
    // return CharStreams.toString(new InputStreamReader(input));
    // }
    // }
    //
    // @Test
    // @InSequence(100)
    // public void create() throws Exception {
    // HttpPost post = new HttpPost(BASE_URL);
    // post.setEntity(new StringEntity(XML));
    //
    // HttpResponse response = http.execute(post);
    //
    // this.created = getCreatedIdFromLocationHeader(response);
    //
    // assertFalse(tagsBefore.equals(getTags()));
    // }
    //
    // private Long getCreatedIdFromLocationHeader(HttpResponse response) {
    // String location = response.getFirstHeader("Location").getValue();
    // assertTrue(location.startsWith(BASE_URL));
    // location = location.substring(BASE_URL.length());
    // assertTrue(location.startsWith("tag/"));
    // location = location.substring(4);
    // return Long.valueOf(location);
    // }
    //
    // @Test
    // @InSequence(500)
    // public void getTag() throws IOException {
    // HttpGet get = new HttpGet(BASE_URL + created);
    //
    // HttpResponse response = http.execute(get);
    // String tag = getEntity(response);
    //
    // assertEquals(200, response.getStatusLine().getStatusCode());
    // assertEquals(XML_HEADER + "<tag id=\"" + created + "\" name=\"X\">hiho</tag>", tag);
    // }
    //
    // @Test
    // @InSequence(900)
    // public void deleteTag() throws IOException {
    // HttpDelete delete = new HttpDelete(BASE_URL + created);
    //
    // HttpResponse response = http.execute(delete);
    //
    // assertEquals(200, response.getStatusLine().getStatusCode());
    // assertEquals(tagsBefore, getTags());
    // }
}
