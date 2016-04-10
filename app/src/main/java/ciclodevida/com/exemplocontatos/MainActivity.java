package ciclodevida.com.exemplocontatos;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //O local dos contatos está na URI ContactsContract.Contacts.CONTENT_URI definida pela API Android
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //Esta URI é lida pelo método getContenResolver.query()
        Cursor c = getContentResolver().query(uri, null, null, null, null);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //Os contatos são percorridos dentro de um while(c.moveToNext())
        //while será executado enquanto existir um registro
        while(c.moveToNext()) {

            String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phones = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID, null, null, null);
            phones.moveToNext();
            adaptador.add(c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            adaptador.add(phones.getString(phones.getColumnIndex(Phone.NUMBER)));

            //Log.i("exemplo", c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            //Log.i("exemplo",phones.getString(phones.getColumnIndex(Phone.NUMBER)));
        }

        setListAdapter(adaptador);
        c.close();

    }

}
