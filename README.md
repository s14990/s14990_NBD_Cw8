# s14990_NBD_Cw8
Task 8 for nbd, small app for riak connection
Riak db:
```
docker run -p 8098:8098 -p 8087:8087 -d dasudian/riak
```
Expected output
```
316 [main] INFO com.basho.riak.client.core.RiakNode - RiakNode started; 127.0.0.1:8087
317 [main] INFO com.basho.riak.client.core.RiakCluster - RiakCluster is starting.
319 [main] INFO com.basho.riak.client.core.util.DefaultCharset - No desired charset found in system properties, the default charset 'UTF-8' will be used
320 [main] INFO com.basho.riak.client.core.util.DefaultCharset - Initializing client charset to: UTF-8
After Insert
{'boat':true, 'name':'Y3'}
After Update
{'boat':false, 'name':'Plane'}
Object Deleted
434 [main] INFO com.basho.riak.client.core.RiakCluster - RiakCluster is shutting down.
935 [pool-1-thread-2] INFO com.basho.riak.client.core.RiakCluster - All operations have completed
935 [pool-1-thread-2] INFO com.basho.riak.client.core.RiakNode - RiakNode shutting down; 127.0.0.1:8087
935 [pool-1-thread-1] INFO com.basho.riak.client.core.RiakCluster - Retrier shutting down.
935 [pool-1-thread-2] INFO com.basho.riak.client.core.DefaultNodeManager - NodeManager removed node due to it shutting down; 127.0.0.1:8087
938 [pool-1-thread-1] INFO com.basho.riak.client.core.RiakCluster - RiakCluster has shut down
```
