package dev.yamacraft.garbagebox.app.ui;

import android.util.DisplayMetrics;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleLayoutGameActivity;

import dev.yamacraft.garbagebox.app.R;

/**
 * Created by wataru.yamada on 2014/04/08.
 */
public class AndEngineActicity extends SimpleLayoutGameActivity {

    @Override
    public EngineOptions onCreateEngineOptions() {
        // onCreate()より前に、このメソッドが呼ばれます

        // ここで端末解像度の大きさを取得
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // 画面サイズの設定
        final Camera camera = new Camera(0, 0, metrics.widthPixels,
                metrics.heightPixels);
        EngineOptions options = new EngineOptions(true,
                ScreenOrientation.PORTRAIT_FIXED,
                new RatioResolutionPolicy(
                        metrics.widthPixels, metrics.heightPixels), camera);

        return options;
    }

    @Override
    protected void onCreateResources() {
    }

    @Override
    protected Scene onCreateScene() {
        // 表示するSceneクラスの取り込み
        AndEngineScene scene = new AndEngineScene(this);
        return scene;
    }

    /**
     * 利用するレイアウトファイルのリソースIDを設定
     */
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_andengine;
    }

    /**
     * RenderSurfaceViewのリソースIDを設定
     */
    @Override
    protected int getRenderSurfaceViewID() {
        return R.id.renderSurfaceView;
    }

}