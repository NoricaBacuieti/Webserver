import org.junit.*;
import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class WebServerTest {

    public String webserver_root_directory = "/Users/noric/Desktop/Pages";
    public String webserver_maintenance_directory="/Users/noric/Desktop/Pages/maintenance";
    public String webserver_status = "Running";
    public int webserver_port = 8080;

    @Test
    public void update_webserver_port()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_port(7777);
        Assert.assertEquals(7777, webserver.webserver_port);
    }

    @Test
    public void update_webserver_directory_with_completely_correct_directory()
    {

        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_completely_incorrect_directory()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("Norica");
        Assert.assertNotEquals("Norica", webserver.webserver_root_directory);
    }


    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_index_folder()
    {
        //Delete index folder from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_index_html()
    {
        //Delete index.html from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_maintnance_folder()
    {
        //Delete maintenance folder from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_maintnance_html()
    {
        //Delete maintenance.html from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_notfound_folder()
    {
        //Delete notfound folder from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_notfound_html()
    {
        //Delete notfound.html from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_serverdownfolder()
    {
        //Delete serverdown folder from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_serverdown_html()
    {
        //Delete serverdown.html from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }

    @Test
    public void update_webserver_directory_with_incorrect_directory_specifically_no_favicon()
    {
        //Delete favicon.ico from the completely correct directory
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_root_directory("/Users/noric/Desktop/My Pages/Pages");
        Assert.assertNotEquals("/Users/noric/Desktop/My Pages/Pages", webserver.webserver_root_directory);
    }


    @Test
    public void update_webserver_maintenance_directory_with_correct_directory()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_maintenance_directory("/Users/noric/Desktop/My Pages/Pages/Maintenance/");
        Assert.assertEquals("/Users/noric/Desktop/My Pages/Pages/Maintenance/", webserver.webserver_maintenance_directory);
    }

    @Test
    public void update_webserver_maintenance_directory_with_incorrect_directory()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_maintenance_directory("MyMaintenance");
        Assert.assertNotEquals("MyMaintenance", webserver.webserver_maintenance_directory);
    }

    @Test
    public void update_webserver_status()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.update_webserver_status("Maintenance");
        Assert.assertEquals("Maintenance", webserver.webserver_status);

    }

    @Test
    public void path_get_method_with_two_non_null_non_empty_strings()
    {
        Path computed_path = Paths.get("/Users/norica/Desktop","/Pages");
        Assert.assertEquals("\\Users\\norica\\Desktop\\Pages", computed_path.toString());

    }

    @Test
    public void path_get_method_with_two_non_null_strings_both_empty()
    {
        Path computed_path = Paths.get("","");
        Assert.assertEquals("", computed_path.toString());

    }

    @Test
    public void path_get_method_with_two_non_null_strings_second_one_empty()
    {
        Path computed_path = Paths.get("/Users/norica/Desktop","");
        Assert.assertEquals("\\Users\\norica\\Desktop", computed_path.toString());

    }

    @Test
    public void path_get_method_with_two_non_null_strings_first_one_empty()
    {
        Path computed_path = Paths.get("","/Users/norica/Desktop");
        Assert.assertEquals("\\Users\\norica\\Desktop", computed_path.toString());

    }

    @Test(expected = NullPointerException.class)
    public void path_get_method_with_second_string_being_null_and_first_one_average_length()
    {
        Path computed_path = Paths.get("/Users/norica/Desktop",null);

    }

    @Test//Nu ma asteptam la asta, dar bine de stiut
    public void path_get_method_with_firs_string_being_null_and_second_one_average_length()
    {
        Path computed_path = Paths.get(null,"/Users/norica/Desktop");
        Assert.assertEquals("null\\Users\\norica\\Desktop", computed_path.toString());
    }

    @Test (expected = NullPointerException.class)
    public void path_get_method_with_both_strings_null()
    {
        Path computed_path = Paths.get(null,null);
    }

    @Test
    public void path_get_method_with_one_string()
    {
        Path computed_path = Paths.get("/Users/norica/Desktop");
        Assert.assertEquals("\\Users\\norica\\Desktop", computed_path.toString());
    }

    @Test//Interesant
    public void path_get_method_with_non_null_non_empty_strings_odd_format()
    {
        Path computed_path = Paths.get("Hey","Hey");
        Assert.assertEquals("Hey\\Hey", computed_path.toString());
    }

    @Test
    public void getFilePath_index_html_requested_by_slash()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        Path computed_path_by = webserver.getFilePath("/");
        Assert.assertEquals("\\Users\\noric\\Desktop\\Pages\\index\\index.html",computed_path_by.toString());

    }

    @Test
    public void getFilePath_index_css_requested()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        Path computed_path_by = webserver.getFilePath("/index.css");
        Assert.assertEquals("\\Users\\noric\\Desktop\\Pages\\index\\index.css",computed_path_by.toString());

    }

    @Test
    public void getFilePath_favicon_requested()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        Path computed_path_by = webserver.getFilePath("/favicon.ico");
        Assert.assertEquals("\\Users\\noric\\Desktop\\Pages\\favicon.ico",computed_path_by.toString());

    }

    @Test
    public void getFilePath_a_complete_existent_path_requested()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        Path computed_path_by = webserver.getFilePath("/Users/noric/Desktop/Pages/p1/p1.html");
        Assert.assertEquals("\\Users\\noric\\Desktop\\Pages\\p1\\p1.html",computed_path_by.toString());
    }

    @Test
    public void getFilePath_inexistent_odd_path_requested()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        Path computed_path_by = webserver.getFilePath("Hey");
        Assert.assertEquals("Hey",computed_path_by.toString());
    }

    @Test
    public void path_exists_method_finds_existing_path()
    {
        //Trying to find an existing path
        Path path = Paths.get(webserver_root_directory);
        Assert.assertTrue(Files.exists(path));
    }

    @Test
    public void path_exists_method_doesnt_find_inexistent_path()
    {
        //Trying to find an inexistent path
        Path path = Paths.get("Norica");
        Assert.assertFalse(Files.exists(path));
    }

    @Test(expected = NullPointerException.class)
    public void path_exists_method_null_path()
    {

        Path path = Paths.get(null);
    }




    @Test
    public void sendResponse_with_no_exception_thrown_page_accepted_and_correctly_displayed() throws IOException {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = serverSocket.accept();
        Path correct_path = Paths.get("/Users/noric/Desktop/Pages/index/index.html");

        OutputStream out = clientSocket.getOutputStream();
        //trebuie accesat localhost:8080 in browser ca sa se finalizeze testul
        webserver.sendResponse(out,"200 OK", "text/html",Files.readAllBytes(correct_path));//html only
        out.close();

    }

    @Test(expected =  IOException.class)
    public void sendResponse_with_exception_thrown() throws IOException
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = serverSocket.accept();
        Path correct_path = Paths.get("/Users/noric/Desktop/Pages/index/index.html");

        OutputStream out = clientSocket.getOutputStream();
        out.close();//exception is given because the client disconnected
        //trebuie accesat localhost:8080 in browser ca sa se finalizeze testul
        webserver.sendResponse(out,"200 OK", "text/html",Files.readAllBytes(correct_path));//html only

    }

    @Test
    public void handleClient_with_null_client()
    {
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.handleClient(null);
    }

    //cant force it to not enter the while with the readline -- this is impossible since when you connect, you automatically send some request

    @Test
    public void handleClient_with_running_server_and_request_for_index_page() throws IOException
    {
        //Need to connect to localhost:8080
        //You will request the index
        WebServer.handledRequest_s = "";
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //html

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //css

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //favicon

        //Due to how the handlerequests attribute is constructed, the first character will be a space
        Assert.assertEquals(" \\Users\\noric\\Desktop\\Pages\\index\\index.html \\Users\\noric\\Desktop\\Pages\\index\\index.css \\Users\\noric\\Desktop\\Pages\\favicon.ico",WebServer.handledRequest_s);

        WebServer.handledRequest_s ="";

    }
    @Test
    public void handleClient_with_running_server_and_request_for_unknown_page() throws IOException
    {
        //Need to connect to localhost:8080
        //You will request Users/noric(your name)/Desktop/Pages/index/bubu.html
        WebServer.handledRequest_s = "";
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //html

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //css

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //favicon

        //Due to how the handlerequests attribute is constructed, the first character will be a space
        Assert.assertEquals(" \\Users\\noric\\Desktop\\Pages\\index\\bubu.html \\Users\\noric\\Desktop\\Pages\\notfound\\notfound.css \\Users\\noric\\Desktop\\Pages\\favicon.ico",WebServer.handledRequest_s);

        WebServer.handledRequest_s ="";

    }

    @Test
    public void handleClient_with_maintenance_server_and_request_for_index_page() throws IOException
    {
        //Need to connect to localhost:8080
        //You will request the index

        WebServer.handledRequest_s = "";
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, "Maintenance");
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //html

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //css

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //favicon

        //Due to how the handlerequests attribute is constructed, the first character will be a space
        Assert.assertEquals(" \\Users\\noric\\Desktop\\Pages\\index\\index.html \\Users\\noric\\Desktop\\Pages\\maintenance\\maintenance.css \\Users\\noric\\Desktop\\Pages\\favicon.ico",WebServer.handledRequest_s);

        WebServer.handledRequest_s ="";

    }
    @Test
    public void handleClient_with_stopped_server_and_request_for_index_page() throws IOException
    {
        //Need to connect to localhost:8080
        //You will request the index
        WebServer.handledRequest_s = "";
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, "Stopped");
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //html

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //css

        clientSocket = serverSocket.accept();
        webserver.handleClient(clientSocket); //favicon

        //Due to how the handlerequests attribute is constructed, the first character will be a space
        Assert.assertEquals(" \\Users\\noric\\Desktop\\Pages\\index\\index.html \\Users\\noric\\Desktop\\Pages\\serverdown\\serverdown.css \\Users\\noric\\Desktop\\Pages\\favicon.ico",WebServer.handledRequest_s);

        WebServer.handledRequest_s ="";

    }

    @Test
    public void handleClient_with_running_server_and_request_for_index_page_client_disconnecting_IO_Exception_Thrown() throws IOException
    {
        //Need to connect to localhost:8080
        //You will request the index

        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        ServerSocket serverSocket = new ServerSocket(8080);

        Socket clientSocket = serverSocket.accept();
        clientSocket.close();
        webserver.handleClient(clientSocket); //html
        //Want to get the IOException in handleClient

    }


    @Test
    public void handle_one_request_with_running_server_handling_known_page_request_no_exception_occuring()
    {
        //Need to connect to localhost:8080
        //You will request the index

        WebServer.handledRequest_s ="";
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.handle_one_request(); //html
        webserver.handle_one_request(); //css
        webserver.handle_one_request(); //favicon

        Assert.assertEquals(" \\Users\\noric\\Desktop\\Pages\\index\\index.html \\Users\\noric\\Desktop\\Pages\\index\\index.css \\Users\\noric\\Desktop\\Pages\\favicon.ico",WebServer.handledRequest_s);
        WebServer.handledRequest_s="";

    }
    @Test
    public void handle_one_request_with_running_server_IOException_when_creating_a_serverSocket()
    {

        //netstat -ab and choose the some port for which it says that it's established
        //Put the found established port in the constructor
        //You don;t have to access anything -- just wait -- if the port is still established at the time of execution, you will see the message "Could not listen on port: xxxxxx"
        WebServer webserver = new WebServer(50546, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        webserver.handle_one_request(); //html
        webserver.handle_one_request(); //css
        webserver.handle_one_request(); //favicon


    }

    @Test
    public void handle_one_request_with_running_server_IOException_when_two_clients_connect_at_same_server() throws BrokenBarrierException, InterruptedException {

        //You don;t have to run anything
        WebServer webserver = new WebServer(webserver_port, webserver_root_directory, webserver_maintenance_directory, webserver_status);
        final CyclicBarrier gate = new CyclicBarrier(2 + 1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    gate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                webserver.handle_one_request(); //html

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    gate.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                webserver.handle_one_request(); //html
            }
        });
        t1.start();
        t2.start();

        gate.await();
        //Could not listen on port

    }

    //Also tests on Main?
}