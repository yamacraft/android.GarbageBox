package dev.yamacraft.garbagebox.app.ui;

import android.graphics.BitmapFactory;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.SimpleLayoutGameActivity;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wataru.yamada on 2014/04/08.
 */
public class AndEngineScene extends Scene {

    private SimpleLayoutGameActivity mActivity;
    private Sprite mSprite;

    /**
     * コンストラクタ
     */
    public AndEngineScene(SimpleLayoutGameActivity activity) {
        super();
        this.mActivity = activity;

        // ----------------------------------------------------
        // スプライトを設定して、画面中央に表示
        // ----------------------------------------------------

        // スプライト元になる画像ファイルの読み込み
        // ただし使うのは画像のサイズだけ
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        InputStream is = null;
        try {
            is = mActivity.getResources().getAssets().open("img/jellybean.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(is, null, options);

        // Spriteに貼り付けるテクスチャーの生成
        BitmapTextureAtlas bta = new BitmapTextureAtlas(
                mActivity.getTextureManager(),
                getTwoPowerSize(options.outWidth),
                getTwoPowerSize(options.outHeight),
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mActivity.getEngine().getTextureManager().loadTexture(bta);

        ITextureRegion btr = BitmapTextureAtlasTextureRegionFactory
                .createFromAsset(bta, mActivity, "img/jellybean.png", 0, 0);

        // Spriteの作成と画面中央に配置
        mSprite = new Sprite(0, 0, btr,
                mActivity.getVertexBufferObjectManager());
        mSprite.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        mSprite.setPosition(
                (mActivity.getEngine().getCamera().getWidth() - mSprite
                        .getWidth()) / 2, (mActivity.getEngine().getCamera()
                        .getHeight() - mSprite.getHeight()) / 2);
        attachChild(mSprite);

        // タイマーハンドラーの設定
        registerUpdateHandler(timerHandler);
    }

    /*
     * 1/60秒単位で呼ばれるTimerHandler
     */
    private TimerHandler timerHandler = new TimerHandler(1f / 60, true,
            new ITimerCallback() {
                @Override
                public void onTimePassed(TimerHandler pTimerHandler) {

                    // Spriteを上に移動させる
                    mSprite.setPosition(mSprite.getX(), mSprite.getY() - 5);

                    // Spriteが画面から完全に消えたら、画面最下部に移動させる
                    if (mSprite.getY() <= 0 - mSprite.getHeight()) {
                        mSprite.setPosition(mSprite.getX(), mActivity
                                .getEngine().getCamera().getHeight()
                                + mSprite.getHeight());
                    }
                }
            });

    /**
     * 指定したサイズより１つ上の2のべき乗の値を返す
     *
     * @param size
     * @return
     */
    private int getTwoPowerSize(float size) {
        int value = (int) (size + 1);
        int pow2value = 64;
        while (pow2value < value)
            pow2value *= 2;
        return pow2value;
    }
}