import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class WebServer extends Thread
{

    public static String handledRequest_s = "";
    public int webserver_port = 8080;
    public String webserver_root_directory = "/Users/noric/Desktop/Pages"; //this contains index/index.html
    public String webserver_maintenance_directory = "/Users/noric/Desktop/Pages/maintenance";//this contains maintenance.html
    public String webserver_status  = "Stopped";

    public WebServer(int webserver_port, String webserver_root_directory, String webserver_maintenance_directory, String webserver_status)
    {
        this.webserver_port=webserver_port;
        this.webserver_root_directory= webserver_root_directory;
        this.webserver_maintenance_directory=webserver_maintenance_directory;
        this.webserver_status=webserver_status;
    }

    public void update_webserver_port(int new_webserver_port)
    {
        webserver_port= new_webserver_port;
    }

    public void update_webserver_root_directory(String new_webserver_root_directory)
    {
        if(     Files.exists(Paths.get(new_webserver_root_directory,"/index"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/index/index.html"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/maintenance"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/maintenance/maintenance.html"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/notfound"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/notfound/notfound.html"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/serverdown"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/serverdown/serverdown.html"))
                && Files.exists(Paths.get(new_webserver_root_directory,"/favicon.ico")))
            webserver_root_directory = new_webserver_root_directory;
        else
            System.out.println("Root directory requirements not met");
    }

    public void update_webserver_maintenance_directory(String new_webserver_maintenance_directory)
    {
        if(Files.exists(Paths.get(new_webserver_maintenance_directory,"/maintenance.html")) )
            webserver_maintenance_directory= new_webserver_maintenance_directory;
        else
            System.out.println("Maintenance root directory requirements");
    }

    public void update_webserver_status(String new_webserver_status)
    {
        webserver_status= new_webserver_status;
    }

    public Path getFilePath(String path)
    {

        if ("/".equals(path))
        {
            return Paths.get(webserver_root_directory,"/index/index.html");
        }
        else if("/index.css".equals(path))
        {
            return Paths.get(webserver_root_directory,"/index/index.css");
        }
        else if("/favicon.ico".equals(path))
        {
            return Paths.get(webserver_root_directory,path);
        }
        else
            return Paths.get(path);
    }

    public void sendResponse(OutputStream out, String status, String contentType, byte[] content) throws IOException
    {
        out.write(("HTTP/1.1 \r\n" + status).getBytes());
        out.write(("ContentType: " + contentType + "\r\n").getBytes());
        out.write("\r\n".getBytes());
        out.write(content);
        out.write("\r\n\r\n".getBytes());
    }

    public void handleClient(Socket clientSocket) {

        try
        {

            try {
                OutputStream out = clientSocket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                ArrayList<String> inputLines = new ArrayList<String>();
                while ((inputLine = in.readLine()) != null)
                {
                    inputLines.add(inputLine);

                    if (inputLine.trim().equals(""))
                        break;
                }

                if (inputLines.size() != 0)
                {
                    String raw_path = inputLines.get(0).split(" ")[1];
                    Path filePath = getFilePath(raw_path);
                    String contentType = Files.probeContentType(filePath);

                    handledRequest_s = handledRequest_s + " " + filePath.toString();
                    if (webserver_status.equals("Running"))
                    {
                        if (Files.exists(filePath))
                            sendResponse(out, "200 OK", contentType, Files.readAllBytes(filePath));
                        else
                            sendResponse(out, "404 Not Found", contentType, Files.readAllBytes(Paths.get(webserver_root_directory, "/notfound/notfound.html")));
                    }
                    else if (webserver_status.equals("Maintenance"))
                    {
                        if (contentType.contains("html"))
                            sendResponse(out, "503 Service Unavailable", contentType, Files.readAllBytes(Paths.get(webserver_maintenance_directory, "/maintenance.html")));
                        else
                            sendResponse(out, "200 OK", contentType, Files.readAllBytes(filePath));
                    }
                    else
                        {
                        if (contentType.contains("html"))
                            sendResponse(out, "503 Service Unavailable", contentType, Files.readAllBytes(Paths.get(webserver_root_directory, "/serverdown/serverdown.html")));
                        else
                            sendResponse(out, "200 OK", contentType, Files.readAllBytes(filePath));
                    }
                }


                out.close();
                in.close();
                }
            catch(NullPointerException e)
            {
                System.err.println("Null client object was given");
            }
        }
        catch (IOException e)
        {
            System.err.println("Problem with Communication Server");
        }
    }

    public void handle_one_request()
    {
        try
        {

            ServerSocket serverSocket = new ServerSocket(webserver_port);
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            //Port is already busy -- either because the system took it before me, or because a client has taken it and another clint wants to connect to it while the request of the first client is being processed -- basically two clients connecting at the same time
            System.err.println("Could not listen on port:"+ webserver_port);
        }

    }
}


class Mainul
{
    public static void main(String[] args)
    {


        WebServer webserver = new WebServer(8080,"/Users/noric/Desktop/Pages","/Users/noric/Desktop/Pages/maintenance", "Stopped");
        webserver.update_webserver_status("Running");
        while(true)
        {
            webserver.handle_one_request();
        }
    }
}
