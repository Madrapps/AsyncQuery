# AsyncQuery
[![Build Status](https://travis-ci.org/Madrapps/AsyncQuery.svg?branch=master)](https://travis-ci.org/Madrapps/AsyncQuery)
[ ![Download](https://api.bintray.com/packages/madrapps/maven/com.github.madrapps%3Aasyncquery/images/download.svg) ](https://bintray.com/madrapps/maven/com.github.madrapps%3Aasyncquery/_latestVersion)

Improved AsyncQueryHandler that handles BulkInsert operation

**Note:** This is literally a copy paste of the `AsyncQueryHandler` in Android and then modified that to support Bulk Insert operation. All credits goes to the Android Open Source team for writing the original `AsyncQueryHandler`.

Download
-----

```gradle
repositories {
  jcenter() // or mavenCentral()
}

dependencies {
  compile 'com.github.madrapps:asyncquery:1.0.0'
}
```

Usage
-----
You would use this the same way as you would use `AsyncQueryHandler` except you can now use `startBulkInsert()`

```java
final DatabaseHandler handler = new DatabaseHandler(getContentResolver());
final Uri uri = new Uri.Builder().scheme(SCHEME).authority(AUTHORITY).appendEncodedPath(ORGANIZATION).build();
handler.startBulkInsert(1, null, uri, getContentValues());
```

```java
public class DatabaseHandler extends AsyncQueryHandler {

    public DatabaseHandler(ContentResolver cr) {
        super(cr);
    }

    @Override
    protected void onBulkInsertComplete(int token, Object cookie, int result) {
        super.onBulkInsertComplete(token, cookie, result);
        Log.d("DatabaseHandler", "Bulk Insert Done");
    }
}
```

License
-----

AsyncQuery by [Madrapps](http://madrapps.github.io/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0) by Android Open Source Platform. 
