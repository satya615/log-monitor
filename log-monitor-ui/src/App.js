import { useEffect, useState } from "react";

import SockJS from "sockjs-client";

import { Client } from "@stomp/stompjs";

function App() {

  const [logs, setLogs] = useState([]);

  useEffect(() => {

    const socket =
        new SockJS("http://localhost:8080/ws");

    const client = new Client({

      webSocketFactory: () => socket,

      reconnectDelay: 5000,

      onConnect: () => {

        console.log("CONNECTED");

        client.subscribe(
            "/topic/logs",
            (message) => {

              const log =
                  JSON.parse(message.body);

              console.log(log);

              setLogs(prev => [log, ...prev]);
            }
        );
      }
    });

    client.activate();

    return () => {
      client.deactivate();
    };

  }, []);

  return (

      <div style={{ padding: 20 }}>

        <h1>AI Log Monitor</h1>

        {
          logs.map((log, index) => (

              <div
                  key={index}
                  style={{
                    border: "1px solid gray",
                    marginBottom: 10,
                    padding: 10
                  }}
              >

                <h3>{log.level}</h3>

                <p>{log.message}</p>

                <small>{log.applicationName}</small>

              </div>
          ))
        }

      </div>
  );
}

export default App;