package flutter.moum.hardware_buttons

import android.app.Application
import android.util.Log
import io.flutter.plugin.common.EventChannel

class HomeButtonStreamHandler(private val application: Application) : EventChannel.StreamHandler {

    private var mStreamSink: EventChannel.EventSink? = null

    private val mHomeButtonListener = object : HardwareButtonsWatcherManager.HomeButtonListener {
        override fun onHomeButtonEvent() {
            Log.d("TAG", "CLOSED")
            mStreamSink?.success(0)
            
            Toast.makeText(getApplicationContext(), "Home Button is Pressed", Toast.LENGTH_SHORT).show();           
        }
    }

    override fun onListen(args: Any?, sink: EventChannel.EventSink?) {
        this.mStreamSink = sink
        Log.d("TAG", "LISTEN")
        Toast.makeText(getApplicationContext(), "Home Button is Listen", Toast.LENGTH_SHORT).show();
        HardwareButtonsWatcherManager.getInstance(application).addHomeButtonListener(mHomeButtonListener)
    }

    override fun onCancel(args: Any?) {
        this.mStreamSink = null
        HardwareButtonsWatcherManager.getInstance(application).removeHomeButtonListener(mHomeButtonListener)
    }
}
