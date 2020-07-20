package ru.hawoline.onepiecemangareader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity implements MainView {
    private Manga onePieceManga;
    private Chapter currentChapter;

    private RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder> chaptersAdapter;
    private RecyclerView.Adapter<FramesAdapter.FramesViewHolder> framesAdapter;
    private RecyclerView chapters_recycler_view, frames_recycler_view;

    private OnePieceMangaParser onePieceMangaParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChaptersAdapter.mainView = this;

        onePieceManga = new Manga("https://one-pieceonline.com/");

        chapters_recycler_view = findViewById(R.id.chapters_recycler_view);
        frames_recycler_view = findViewById(R.id.frames_recycler_view);

        chaptersAdapter = new ChaptersAdapter(onePieceManga.getChapters());
        chapters_recycler_view.setAdapter(chaptersAdapter);

        currentChapter = new Chapter("", "");
        framesAdapter = new FramesAdapter(currentChapter);
        frames_recycler_view.setAdapter(framesAdapter);

        onePieceMangaParser = new OnePieceMangaParser();

        onePieceMangaParser.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();

        onePieceMangaParser.cancel(true);
    }

    @Override
    public void showChapter(Chapter currentChapter) {
        this.currentChapter = currentChapter;

        chapters_recycler_view.setVisibility(View.GONE);
        frames_recycler_view.setVisibility(View.VISIBLE);

        OnePieceChapterParser onePieceChapterParser = new OnePieceChapterParser();
        onePieceChapterParser.execute();
    }

    @Override
    public void showManga() {
        chapters_recycler_view.setVisibility(View.VISIBLE);
        frames_recycler_view.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (frames_recycler_view.getVisibility() == View.VISIBLE) {
            showManga();
        } else {
            finish();
        }
    }

    public class OnePieceMangaParser extends AsyncTask<String, Void, String> {
        Document doc;

        Elements linkElements;

        @Override
        protected String doInBackground(String... arg) {

            try {
                doc = Jsoup.connect(onePieceManga.getBaseUrl()).get();
                linkElements = doc.select("a");
                ArrayList<Chapter> onePieceChapters = new ArrayList<>();

                for (Element element: linkElements) {
                    String title = element.text();
                    if (title.contains("One Piece,")){
                        String link = element.attr("href");
                        onePieceChapters.add(new Chapter(title, link));
                    }
                }

                for (int latestChapter = 0; latestChapter < 10; latestChapter++) {
                    onePieceChapters.remove(onePieceChapters.size() - 1);
                }
                onePieceManga.setChapters(onePieceChapters);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            chaptersAdapter = new ChaptersAdapter(onePieceManga.getChapters());
            chapters_recycler_view.setAdapter(chaptersAdapter);
        }

    }
    public class OnePieceChapterParser extends AsyncTask<String, Void, String> {
        Document document;

        Elements pagesElements;
        Elements linkElements;
        Elements frameElements;

        @Override
        protected String doInBackground(String... strings) {
            try {
                document = Jsoup.connect(currentChapter.getUrl()).get();
                ArrayList<String> images = new ArrayList<>();

                pagesElements = document.select("p.separator");
                if (pagesElements.isEmpty()) {
                    pagesElements = document.select("div.separator");
                }
                linkElements = pagesElements.select("a");
                frameElements = linkElements.select("img");

                for (Element image: frameElements) {
                    images.add(image.attr("src"));
                }

                currentChapter.setFrames(images);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            framesAdapter = new FramesAdapter(currentChapter);
            frames_recycler_view.setAdapter(framesAdapter);
        }
    }
}
