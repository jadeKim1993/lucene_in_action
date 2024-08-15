package lia;

import java.io.IOException;

import lia.common.AnalyzerUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.ngram.EdgeNGramTokenFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;

public class NGramFilterSample {
  private static class NGramAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
      Tokenizer src = new KeywordTokenizer();
      TokenStream result = new NGramTokenFilter(src, 2, 4, true);
      return new TokenStreamComponents(src, result);
    }
  }

  private static class EdgeNGramAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
      Tokenizer src = new KeywordTokenizer();
      TokenStream result = new EdgeNGramTokenFilter(src, 1, 4, true);
      return new TokenStreamComponents(src, result);
    }
  }

  public static void main(String... args) throws IOException {
    if (args.length != 1) {
      throw new IllegalArgumentException("Usage: java " + NGramFilterSample.class.getName() + " <spell checker index dir> <word>");
    }
    AnalyzerUtils.displayTokensWithPositions(new NGramAnalyzer(), args[0]);
    AnalyzerUtils.displayTokensWithPositions(new EdgeNGramAnalyzer(), args[0]);
  }
}
