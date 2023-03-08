package com.parkging.helloblog;

import com.parkging.helloblog.domain.Category;
import com.parkging.helloblog.domain.Member;
import com.parkging.helloblog.domain.Post;
import com.parkging.helloblog.repository.PostRepository;
import com.parkging.helloblog.service.CategoryService;
import com.parkging.helloblog.service.MemberService;
import com.parkging.helloblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PostRepository postRepository;

//    @PostConstruct
    public void init() {

        Member member = Member.builder()
                .email("parkjun611@naver.com")
                .name("박준영")
                .password("dkansk11")
                .build();

        memberService.join(member.getName(), member.getEmail(), member.getPassword());

        Long parentId = categoryService.addCategory("개발", null);
        categoryService.addCategory("자바", parentId);
        categoryService.addCategory("스프링", parentId);
        categoryService.addCategory("타임리프", parentId);

        Long parentId2 = categoryService.addCategory("일상", null);
        categoryService.addCategory("여행", parentId2);
        categoryService.addCategory("생각", parentId2);

        Category category = categoryService.findByName("여행");

        for (int i = 0; i < 26; i++) {
//            Post post1 = getPost1(category);
//            Post post2 = getPost2(category);
            Post post3 = getPost3(i+1, category);
//            postRepository.save(post1);
//            postRepository.save(post2);
            postRepository.save(post3);
        }

    }

    private static Post getPost3(Integer i,Category category) {
        Post post2 = Post.builder()
                .category(category)
                .title(Integer.toString(i))
                .content("<div class=\"contents_style\"><h1>맥 세팅</h1>\n" +
                        "<p>M2 노트북으로 바꾸고 세팅과정을 정리해봤다.</p>\n" +
                        "<h2>키보드/단축키 설정</h2>\n" +
                        "<h3>karabiner</h3>\n" +
                        "<ul>\n" +
                        "<li>설치: <a href=\"https://karabiner-elements.pqrs.org/\">https://karabiner-elements.pqrs.org/</a></li>\n" +
                        "<li><code>For all devices</code> &gt; <code>right_command</code> 를 <code>f18</code>로 매핑<br><img src=\"https://blog.kakaocdn.net/dn/bR2DaB/btrNdTTIaxj/rtrtKuFgJtpQaFW0Fd19L1/img.png\" alt=\"\"><br><img src=\"https://blog.kakaocdn.net/dn/ddzg42/btrNcX3jz5g/NYk018YuKn6tA7MOTOODe1/img.png\" alt=\"\"></li>\n" +
                        "<li>개인 키보드(Realforce 87)에서 아래와 같이 키 변경<ul>\n" +
                        "<li>left_command &gt; left_option</li>\n" +
                        "<li>left_option &gt; left_command</li>\n" +
                        "<li>right_option &gt; f18<br><figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-origin-width=\"2424\" data-origin-height=\"1066\"><span data-url=\"https://blog.kakaocdn.net/dn/duo1UJ/btrNhdRpPcl/l8zkK3AHoIfacDjKBD84V1/img.png\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/duo1UJ/btrNhdRpPcl/l8zkK3AHoIfacDjKBD84V1/img.png\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fduo1UJ%2FbtrNhdRpPcl%2Fl8zkK3AHoIfacDjKBD84V1%2Fimg.png\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-origin-width=\"2424\" data-origin-height=\"1066\"></span></figure>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<h3>맥 키보드 설정</h3>\n" +
                        "<ul>\n" +
                        "<li>키보드 → 키 반복 빠르게로 변경</li>\n" +
                        "<li>단축키 → Spotlight → Spotlight 검색 보기, Finder 검색 윈도우 보기 단축키 제거</li>\n" +
                        "<li>단축키 → 입력 소스 → 이전 입력 소스 선택을 F18 (right_command)로 설정</li>\n" +
                        "</ul>\n" +
                        "<h2>효율성 앱</h2>\n" +
                        "<ul>\n" +
                        "<li>크롬: <a href=\"https://chrome.com\">https://chrome.com</a></li>\n" +
                        "<li>Alfred: <a href=\"https://www.alfredapp.com/\">https://www.alfredapp.com</a></li>\n" +
                        "<li>Spectacle: <a href=\"https://www.spectacleapp.com/\">https://www.spectacleapp.com</a></li>\n" +
                        "<li>Monosnap: AppStore</li>\n" +
                        "<li>iTerms: <a href=\"https://iterm2.com/\">https://iterm2.com</a><ul>\n" +
                        "<li>dracula 테마 설치 : <a href=\"https://github.com/dracula/iterm\">https://github.com/dracula/iterm</a></li>\n" +
                        "<li>droid sans mono로 폰트 변경</li>\n" +
                        "</ul>\n" +
                        "</li>\n" +
                        "<li>AppCleaner</li>\n" +
                        "<li>Office365</li>\n" +
                        "<li>OneDrive</li>\n" +
                        "<li>GoogleDrive</li>\n" +
                        "<li>thefuck: <a href=\"https://github.com/nvbn/thefuck\">https://github.com/nvbn/thefuck</a></li>\n" +
                        "</ul>\n" +
                        "<h2>폰트</h2>\n" +
                        "<ul>\n" +
                        "<li>consolas: <a href=\"https://freefontsdownload.net/free-consolas-font-33098.htm\">https://freefontsdownload.net/free-consolas-font-33098.htm</a></li>\n" +
                        "<li>네이버 폰트: <a href=\"https://hangeul.naver.com/font\">https://hangeul.naver.com/font</a></li>\n" +
                        "<li>Droid sans mono: <a href=\"https://www.fontsquirrel.com/fonts/droid-sans-mono\">https://www.fontsquirrel.com/fonts/droid-sans-mono</a></li>\n" +
                        "</ul>\n" +
                        "<h2>개발/패키지 도구</h2>\n" +
                        "<h3>명령어 개발도구</h3>\n" +
                        "<pre><code class=\"hljs lua\">$ code-<span class=\"hljs-built_in\">select</span> <span class=\"hljs-comment\">--install</span></code></pre><h3>Homebrew</h3>\n" +
                        "<ul>\n" +
                        "<li>주소: <a href=\"https://brew.sh/index_ko\">https://brew.sh/index_ko</a></li>\n" +
                        "<li>몇 가지 앱 설치</li>\n" +
                        "<li><code>$ brew install curl wget tmux</code></li>\n" +
                        "</ul>\n" +
                        "<h3>bash 설정</h3>\n" +
                        "<p>시스템설정 → 사용자 및 그룹 → 본인 계정선택 및 잠금해제 → 고급 옵션</p>\n" +
                        "<p></p><figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-origin-width=\"1560\" data-origin-height=\"1294\"><span data-url=\"https://blog.kakaocdn.net/dn/6SUKm/btrNdRVQFOG/jgTRk1gBmbEi9vWP6mVvC0/img.png\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/6SUKm/btrNdRVQFOG/jgTRk1gBmbEi9vWP6mVvC0/img.png\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F6SUKm%2FbtrNdRVQFOG%2FjgTRk1gBmbEi9vWP6mVvC0%2Fimg.png\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-origin-width=\"1560\" data-origin-height=\"1294\"></span></figure>\n" +
                        "<p></p>\n" +
                        "<h3>zsh 설정</h3>\n" +
                        "<ul>\n" +
                        "<li>oh-my-zsh 설치: <a href=\"https://ohmyz.sh/#install\">https://ohmyz.sh/#install</a></li>\n" +
                        "<li>드라큐라 테마 설치: <a href=\"https://draculatheme.com/zsh\">https://draculatheme.com/zsh</a><code>$DRACULA_THEME</code>: 해당 프로젝트를 clone받은 절대 경로<code>$DRACULA_THEME</code>: 해당 프로젝트를 clone받은 절대 경로<br><code>$OH_MY_ZSH</code>: ~/.oh-my-zsh 에 해당하는 경로</li>\n" +
                        "<li><code>$ git clone https://github.com/dracula/zsh.git $ln -s $DRACULA_THEME/dracula.zsh-theme $OH_MY_ZSH/themes/dracula.zsh-theme`</code></li>\n" +
                        "<li>터미널에 git 상태 추가<br>기본적으로는 git 프로젝트에서 branch명만 나온다. 전체 상태를 보이기 위해 .zsh를 수정한다.</li>\n" +
                        "<li><code>plugins=(git git-prompt) # git-prompt 추가 DRACULA_DISPLAY_GIT=0 RPROMPT='' PROMPT+='$(git_super_status)'</code></li>\n" +
                        "</ul>\n" +
                        "<h3>sdkman</h3>\n" +
                        "<p>주소: <a href=\"https://sdkman.io/\">https://sdkman.io</a></p>\n" +
                        "<pre><code class=\"hljs ruby\"><span class=\"hljs-variable\">$ </span>curl -s <span class=\"hljs-string\">\"https://get.sdkman.io\"</span> <span class=\"hljs-params\">| bash</span></code></pre><ul>\n" +
                        "<li><p>AdoptOpenJDK 11버전 설치</p>\n" +
                        "<pre><code class=\"hljs perl\">$ sdk list | <span class=\"hljs-keyword\">grep</span> tem $ sdk install java <span class=\"hljs-number\">11.0</span>.<span class=\"hljs-number\">16</span>-tem</code></pre></li>\n" +
                        "</ul>\n" +
                        "<h3>NVM</h3>\n" +
                        "<ul>\n" +
                        "<li><p>설치주소: <a href=\"https://github.com/nvm-sh/nvm\">https://github.com/nvm-sh/nvm</a></p>\n" +
                        "</li>\n" +
                        "<li><p>nvmrc 적용 <code>~/.zshrc</code>에 추가</p>\n" +
                        "<pre><code class=\"language-bash hljs\"><span class=\"hljs-built_in\">autoload</span> -U add-zsh-hook\n" +
                        "load-<span class=\"hljs-function\"><span class=\"hljs-title\">nvmrc</span></span>() {\n" +
                        " <span class=\"hljs-built_in\">local</span> node_version=<span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm version)</span>\"</span>\n" +
                        " <span class=\"hljs-built_in\">local</span> nvmrc_path=<span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm_find_nvmrc)</span>\"</span>\n" +
                        "\n" +
                        " <span class=\"hljs-keyword\">if</span> [ -n <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_path</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "   <span class=\"hljs-built_in\">local</span> nvmrc_node_version=$(nvm version <span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(cat <span class=\"hljs-string\">\"<span class=\"hljs-variable\">${nvmrc_path}</span>\"</span>)</span>\"</span>)\n" +
                        "\n" +
                        "   <span class=\"hljs-keyword\">if</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_node_version</span>\"</span> = <span class=\"hljs-string\">\"N/A\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "     nvm install\n" +
                        "   <span class=\"hljs-keyword\">elif</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_node_version</span>\"</span> != <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$node_version</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "     nvm use\n" +
                        "   <span class=\"hljs-keyword\">fi</span>\n" +
                        " <span class=\"hljs-keyword\">elif</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$node_version</span>\"</span> != <span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm version default)</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "   <span class=\"hljs-built_in\">echo</span> <span class=\"hljs-string\">\"Reverting to nvm default version\"</span>\n" +
                        "   nvm use default\n" +
                        " <span class=\"hljs-keyword\">fi</span>\n" +
                        "}\n" +
                        "add-zsh-hook chpwd load-nvmrc\n" +
                        "load-nvmrc</code></pre>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<h2>개발관련 앱</h2>\n" +
                        "<ul>\n" +
                        "<li>Intellij: <a href=\"https://www.jetbrains.com/ko-kr/idea/download/#section=mac\">https://www.jetbrains.com/ko-kr/idea/download/#section=ma</a></li>\n" +
                        "<li>Docker: <a href=\"https://www.docker.com/\">https://www.docker.com</a></li>\n" +
                        "<li>ColorSlurp</li>\n" +
                        "<li>Android File Transfer: <a href=\"https://www.android.com/filetransfer/\">https://www.android.com/filetransfer</a></li>\n" +
                        "<li>Charles Proxy: <a href=\"https://www.charlesproxy.com/\">https://www.charlesproxy.com</a></li>\n" +
                        "<li>ec2-gazua: <a href=\"https://pypi.org/project/ec2-gazua/\">https://pypi.org/project/ec2-gazua</a></li>\n" +
                        "</ul>\n" +
                        "<h2>Intellij 플러그인</h2>\n" +
                        "<ul>\n" +
                        "<li>AsciiDoc</li>\n" +
                        "<li>element</li>\n" +
                        "<li>Builder Generator</li>\n" +
                        "<li>Jira Integration</li>\n" +
                        "<li>Handlebars/Mustache</li>\n" +
                        "<li>Key Promoter X</li>\n" +
                        "<li>Korean Language Pack</li>\n" +
                        "<li>Live Edit</li>\n" +
                        "<li>Nginx Configuration</li>\n" +
                        "<li>Vue.js</li>\n" +
                        "<li>Nuxt.js</li>\n" +
                        "<li>PHP</li>\n" +
                        "<li>PHP Annotations</li>\n" +
                        "<li>Prettier</li>\n" +
                        "<li>Python</li>\n" +
                        "<li>React Buddy</li>\n" +
                        "<li>React CSS Modules</li>\n" +
                        "</ul></div>")
                .preview("맥 세팅\n" +
                        "M2 노트북으로 바꾸고 세팅과정을 정리해봤다.\n" +
                        "\n" +
                        "키보드/단축키 설정\n" +
                        "karabiner\n" +
                        "설치: https://karabiner-elements.pqrs.org/\n" +
                        "For all devices > right_command 를 f18로 매핑..")
                .build();
        return post2;
    }

    private static Post getPost2(Category category) {
        Post post2 = Post.builder()
                .category(category)
                .title("맥 환경 세팅")
                .content("<div class=\"contents_style\"><h1>맥 세팅</h1>\n" +
                        "<p>M2 노트북으로 바꾸고 세팅과정을 정리해봤다.</p>\n" +
                        "<h2>키보드/단축키 설정</h2>\n" +
                        "<h3>karabiner</h3>\n" +
                        "<ul>\n" +
                        "<li>설치: <a href=\"https://karabiner-elements.pqrs.org/\">https://karabiner-elements.pqrs.org/</a></li>\n" +
                        "<li><code>For all devices</code> &gt; <code>right_command</code> 를 <code>f18</code>로 매핑<br><img src=\"https://blog.kakaocdn.net/dn/bR2DaB/btrNdTTIaxj/rtrtKuFgJtpQaFW0Fd19L1/img.png\" alt=\"\"><br><img src=\"https://blog.kakaocdn.net/dn/ddzg42/btrNcX3jz5g/NYk018YuKn6tA7MOTOODe1/img.png\" alt=\"\"></li>\n" +
                        "<li>개인 키보드(Realforce 87)에서 아래와 같이 키 변경<ul>\n" +
                        "<li>left_command &gt; left_option</li>\n" +
                        "<li>left_option &gt; left_command</li>\n" +
                        "<li>right_option &gt; f18<br><figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-origin-width=\"2424\" data-origin-height=\"1066\"><span data-url=\"https://blog.kakaocdn.net/dn/duo1UJ/btrNhdRpPcl/l8zkK3AHoIfacDjKBD84V1/img.png\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/duo1UJ/btrNhdRpPcl/l8zkK3AHoIfacDjKBD84V1/img.png\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fduo1UJ%2FbtrNhdRpPcl%2Fl8zkK3AHoIfacDjKBD84V1%2Fimg.png\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-origin-width=\"2424\" data-origin-height=\"1066\"></span></figure>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<h3>맥 키보드 설정</h3>\n" +
                        "<ul>\n" +
                        "<li>키보드 → 키 반복 빠르게로 변경</li>\n" +
                        "<li>단축키 → Spotlight → Spotlight 검색 보기, Finder 검색 윈도우 보기 단축키 제거</li>\n" +
                        "<li>단축키 → 입력 소스 → 이전 입력 소스 선택을 F18 (right_command)로 설정</li>\n" +
                        "</ul>\n" +
                        "<h2>효율성 앱</h2>\n" +
                        "<ul>\n" +
                        "<li>크롬: <a href=\"https://chrome.com\">https://chrome.com</a></li>\n" +
                        "<li>Alfred: <a href=\"https://www.alfredapp.com/\">https://www.alfredapp.com</a></li>\n" +
                        "<li>Spectacle: <a href=\"https://www.spectacleapp.com/\">https://www.spectacleapp.com</a></li>\n" +
                        "<li>Monosnap: AppStore</li>\n" +
                        "<li>iTerms: <a href=\"https://iterm2.com/\">https://iterm2.com</a><ul>\n" +
                        "<li>dracula 테마 설치 : <a href=\"https://github.com/dracula/iterm\">https://github.com/dracula/iterm</a></li>\n" +
                        "<li>droid sans mono로 폰트 변경</li>\n" +
                        "</ul>\n" +
                        "</li>\n" +
                        "<li>AppCleaner</li>\n" +
                        "<li>Office365</li>\n" +
                        "<li>OneDrive</li>\n" +
                        "<li>GoogleDrive</li>\n" +
                        "<li>thefuck: <a href=\"https://github.com/nvbn/thefuck\">https://github.com/nvbn/thefuck</a></li>\n" +
                        "</ul>\n" +
                        "<h2>폰트</h2>\n" +
                        "<ul>\n" +
                        "<li>consolas: <a href=\"https://freefontsdownload.net/free-consolas-font-33098.htm\">https://freefontsdownload.net/free-consolas-font-33098.htm</a></li>\n" +
                        "<li>네이버 폰트: <a href=\"https://hangeul.naver.com/font\">https://hangeul.naver.com/font</a></li>\n" +
                        "<li>Droid sans mono: <a href=\"https://www.fontsquirrel.com/fonts/droid-sans-mono\">https://www.fontsquirrel.com/fonts/droid-sans-mono</a></li>\n" +
                        "</ul>\n" +
                        "<h2>개발/패키지 도구</h2>\n" +
                        "<h3>명령어 개발도구</h3>\n" +
                        "<pre><code class=\"hljs lua\">$ code-<span class=\"hljs-built_in\">select</span> <span class=\"hljs-comment\">--install</span></code></pre><h3>Homebrew</h3>\n" +
                        "<ul>\n" +
                        "<li>주소: <a href=\"https://brew.sh/index_ko\">https://brew.sh/index_ko</a></li>\n" +
                        "<li>몇 가지 앱 설치</li>\n" +
                        "<li><code>$ brew install curl wget tmux</code></li>\n" +
                        "</ul>\n" +
                        "<h3>bash 설정</h3>\n" +
                        "<p>시스템설정 → 사용자 및 그룹 → 본인 계정선택 및 잠금해제 → 고급 옵션</p>\n" +
                        "<p></p><figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-origin-width=\"1560\" data-origin-height=\"1294\"><span data-url=\"https://blog.kakaocdn.net/dn/6SUKm/btrNdRVQFOG/jgTRk1gBmbEi9vWP6mVvC0/img.png\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/6SUKm/btrNdRVQFOG/jgTRk1gBmbEi9vWP6mVvC0/img.png\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F6SUKm%2FbtrNdRVQFOG%2FjgTRk1gBmbEi9vWP6mVvC0%2Fimg.png\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-origin-width=\"1560\" data-origin-height=\"1294\"></span></figure>\n" +
                        "<p></p>\n" +
                        "<h3>zsh 설정</h3>\n" +
                        "<ul>\n" +
                        "<li>oh-my-zsh 설치: <a href=\"https://ohmyz.sh/#install\">https://ohmyz.sh/#install</a></li>\n" +
                        "<li>드라큐라 테마 설치: <a href=\"https://draculatheme.com/zsh\">https://draculatheme.com/zsh</a><code>$DRACULA_THEME</code>: 해당 프로젝트를 clone받은 절대 경로<code>$DRACULA_THEME</code>: 해당 프로젝트를 clone받은 절대 경로<br><code>$OH_MY_ZSH</code>: ~/.oh-my-zsh 에 해당하는 경로</li>\n" +
                        "<li><code>$ git clone https://github.com/dracula/zsh.git $ln -s $DRACULA_THEME/dracula.zsh-theme $OH_MY_ZSH/themes/dracula.zsh-theme`</code></li>\n" +
                        "<li>터미널에 git 상태 추가<br>기본적으로는 git 프로젝트에서 branch명만 나온다. 전체 상태를 보이기 위해 .zsh를 수정한다.</li>\n" +
                        "<li><code>plugins=(git git-prompt) # git-prompt 추가 DRACULA_DISPLAY_GIT=0 RPROMPT='' PROMPT+='$(git_super_status)'</code></li>\n" +
                        "</ul>\n" +
                        "<h3>sdkman</h3>\n" +
                        "<p>주소: <a href=\"https://sdkman.io/\">https://sdkman.io</a></p>\n" +
                        "<pre><code class=\"hljs ruby\"><span class=\"hljs-variable\">$ </span>curl -s <span class=\"hljs-string\">\"https://get.sdkman.io\"</span> <span class=\"hljs-params\">| bash</span></code></pre><ul>\n" +
                        "<li><p>AdoptOpenJDK 11버전 설치</p>\n" +
                        "<pre><code class=\"hljs perl\">$ sdk list | <span class=\"hljs-keyword\">grep</span> tem $ sdk install java <span class=\"hljs-number\">11.0</span>.<span class=\"hljs-number\">16</span>-tem</code></pre></li>\n" +
                        "</ul>\n" +
                        "<h3>NVM</h3>\n" +
                        "<ul>\n" +
                        "<li><p>설치주소: <a href=\"https://github.com/nvm-sh/nvm\">https://github.com/nvm-sh/nvm</a></p>\n" +
                        "</li>\n" +
                        "<li><p>nvmrc 적용 <code>~/.zshrc</code>에 추가</p>\n" +
                        "<pre><code class=\"language-bash hljs\"><span class=\"hljs-built_in\">autoload</span> -U add-zsh-hook\n" +
                        "load-<span class=\"hljs-function\"><span class=\"hljs-title\">nvmrc</span></span>() {\n" +
                        " <span class=\"hljs-built_in\">local</span> node_version=<span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm version)</span>\"</span>\n" +
                        " <span class=\"hljs-built_in\">local</span> nvmrc_path=<span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm_find_nvmrc)</span>\"</span>\n" +
                        "\n" +
                        " <span class=\"hljs-keyword\">if</span> [ -n <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_path</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "   <span class=\"hljs-built_in\">local</span> nvmrc_node_version=$(nvm version <span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(cat <span class=\"hljs-string\">\"<span class=\"hljs-variable\">${nvmrc_path}</span>\"</span>)</span>\"</span>)\n" +
                        "\n" +
                        "   <span class=\"hljs-keyword\">if</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_node_version</span>\"</span> = <span class=\"hljs-string\">\"N/A\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "     nvm install\n" +
                        "   <span class=\"hljs-keyword\">elif</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$nvmrc_node_version</span>\"</span> != <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$node_version</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "     nvm use\n" +
                        "   <span class=\"hljs-keyword\">fi</span>\n" +
                        " <span class=\"hljs-keyword\">elif</span> [ <span class=\"hljs-string\">\"<span class=\"hljs-variable\">$node_version</span>\"</span> != <span class=\"hljs-string\">\"<span class=\"hljs-subst\">$(nvm version default)</span>\"</span> ]; <span class=\"hljs-keyword\">then</span>\n" +
                        "   <span class=\"hljs-built_in\">echo</span> <span class=\"hljs-string\">\"Reverting to nvm default version\"</span>\n" +
                        "   nvm use default\n" +
                        " <span class=\"hljs-keyword\">fi</span>\n" +
                        "}\n" +
                        "add-zsh-hook chpwd load-nvmrc\n" +
                        "load-nvmrc</code></pre>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<h2>개발관련 앱</h2>\n" +
                        "<ul>\n" +
                        "<li>Intellij: <a href=\"https://www.jetbrains.com/ko-kr/idea/download/#section=mac\">https://www.jetbrains.com/ko-kr/idea/download/#section=ma</a></li>\n" +
                        "<li>Docker: <a href=\"https://www.docker.com/\">https://www.docker.com</a></li>\n" +
                        "<li>ColorSlurp</li>\n" +
                        "<li>Android File Transfer: <a href=\"https://www.android.com/filetransfer/\">https://www.android.com/filetransfer</a></li>\n" +
                        "<li>Charles Proxy: <a href=\"https://www.charlesproxy.com/\">https://www.charlesproxy.com</a></li>\n" +
                        "<li>ec2-gazua: <a href=\"https://pypi.org/project/ec2-gazua/\">https://pypi.org/project/ec2-gazua</a></li>\n" +
                        "</ul>\n" +
                        "<h2>Intellij 플러그인</h2>\n" +
                        "<ul>\n" +
                        "<li>AsciiDoc</li>\n" +
                        "<li>element</li>\n" +
                        "<li>Builder Generator</li>\n" +
                        "<li>Jira Integration</li>\n" +
                        "<li>Handlebars/Mustache</li>\n" +
                        "<li>Key Promoter X</li>\n" +
                        "<li>Korean Language Pack</li>\n" +
                        "<li>Live Edit</li>\n" +
                        "<li>Nginx Configuration</li>\n" +
                        "<li>Vue.js</li>\n" +
                        "<li>Nuxt.js</li>\n" +
                        "<li>PHP</li>\n" +
                        "<li>PHP Annotations</li>\n" +
                        "<li>Prettier</li>\n" +
                        "<li>Python</li>\n" +
                        "<li>React Buddy</li>\n" +
                        "<li>React CSS Modules</li>\n" +
                        "</ul></div>")
                .preview("맥 세팅\n" +
                        "M2 노트북으로 바꾸고 세팅과정을 정리해봤다.\n" +
                        "\n" +
                        "키보드/단축키 설정\n" +
                        "karabiner\n" +
                        "설치: https://karabiner-elements.pqrs.org/\n" +
                        "For all devices > right_command 를 f18로 매핑..")
                .build();
        return post2;
    }

    private static Post getPost1(Category category) {
        Post post1 = Post.builder()
                .category(category)
                .title("암사, 광진교")
                .content("<div class=\"contents_style\"><p data-ke-size=\"size16\">&nbsp;</p>\n" +
                        "<p></p><figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02176.webp\" data-origin-width=\"3000\" data-origin-height=\"1981\"><span data-url=\"https://blog.kakaocdn.net/dn/I1XL2/btr1yzsVcPy/i4BKjZuxACAcny9FPoiZf1/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/I1XL2/btr1yzsVcPy/i4BKjZuxACAcny9FPoiZf1/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FI1XL2%2Fbtr1yzsVcPy%2Fi4BKjZuxACAcny9FPoiZf1%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02176.webp\" data-origin-width=\"3000\" data-origin-height=\"1981\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02194.webp\" data-origin-width=\"3000\" data-origin-height=\"2475\"><span data-url=\"https://blog.kakaocdn.net/dn/wMqyf/btr1nww09UH/mPLP3wwnTH6PaB6Ch2p51K/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/wMqyf/btr1nww09UH/mPLP3wwnTH6PaB6Ch2p51K/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FwMqyf%2Fbtr1nww09UH%2FmPLP3wwnTH6PaB6Ch2p51K%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02194.webp\" data-origin-width=\"3000\" data-origin-height=\"2475\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02202.webp\" data-origin-width=\"3000\" data-origin-height=\"1689\"><span data-url=\"https://blog.kakaocdn.net/dn/bOopxX/btr1fOdWxJz/3xAXiZxkNA5peulCLIF2Y0/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/bOopxX/btr1fOdWxJz/3xAXiZxkNA5peulCLIF2Y0/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbOopxX%2Fbtr1fOdWxJz%2F3xAXiZxkNA5peulCLIF2Y0%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02202.webp\" data-origin-width=\"3000\" data-origin-height=\"1689\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02209.webp\" data-origin-width=\"3000\" data-origin-height=\"1920\"><span data-url=\"https://blog.kakaocdn.net/dn/cIKeAq/btr1p1KgB4q/sOUfykvCatK2jHBnuKJBdK/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/cIKeAq/btr1p1KgB4q/sOUfykvCatK2jHBnuKJBdK/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcIKeAq%2Fbtr1p1KgB4q%2FsOUfykvCatK2jHBnuKJBdK%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02209.webp\" data-origin-width=\"3000\" data-origin-height=\"1920\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02215.webp\" data-origin-width=\"3000\" data-origin-height=\"1848\"><span data-url=\"https://blog.kakaocdn.net/dn/cLxSoy/btr1lwD5tAy/NVgAhlQ4br2a4SXAEmgXC1/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/cLxSoy/btr1lwD5tAy/NVgAhlQ4br2a4SXAEmgXC1/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcLxSoy%2Fbtr1lwD5tAy%2FNVgAhlQ4br2a4SXAEmgXC1%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02215.webp\" data-origin-width=\"3000\" data-origin-height=\"1848\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02220.webp\" data-origin-width=\"3000\" data-origin-height=\"1625\"><span data-url=\"https://blog.kakaocdn.net/dn/k9ePD/btr06WDWaSZ/v1KVaREQL7EvysBHfCUIW1/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/k9ePD/btr06WDWaSZ/v1KVaREQL7EvysBHfCUIW1/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fk9ePD%2Fbtr06WDWaSZ%2Fv1KVaREQL7EvysBHfCUIW1%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02220.webp\" data-origin-width=\"3000\" data-origin-height=\"1625\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02222.webp\" data-origin-width=\"3000\" data-origin-height=\"1692\"><span data-url=\"https://blog.kakaocdn.net/dn/biz7CQ/btr1f1xEtEg/1NkFcBn7JNMSE4sgbwIPi0/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/biz7CQ/btr1f1xEtEg/1NkFcBn7JNMSE4sgbwIPi0/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbiz7CQ%2Fbtr1f1xEtEg%2F1NkFcBn7JNMSE4sgbwIPi0%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02222.webp\" data-origin-width=\"3000\" data-origin-height=\"1692\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02226.webp\" data-origin-width=\"3000\" data-origin-height=\"1558\"><span data-url=\"https://blog.kakaocdn.net/dn/b3D6mo/btr06Ww6O9q/8o5nknD9K4kt2Z0bBjN5s1/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/b3D6mo/btr06Ww6O9q/8o5nknD9K4kt2Z0bBjN5s1/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb3D6mo%2Fbtr06Ww6O9q%2F8o5nknD9K4kt2Z0bBjN5s1%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02226.webp\" data-origin-width=\"3000\" data-origin-height=\"1558\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02235.webp\" data-origin-width=\"3000\" data-origin-height=\"1896\"><span data-url=\"https://blog.kakaocdn.net/dn/MR42G/btr1lvZrmfU/k0W0vqS8qkl4Z5jqLy4xNk/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/MR42G/btr1lvZrmfU/k0W0vqS8qkl4Z5jqLy4xNk/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FMR42G%2Fbtr1lvZrmfU%2Fk0W0vqS8qkl4Z5jqLy4xNk%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02235.webp\" data-origin-width=\"3000\" data-origin-height=\"1896\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02245.webp\" data-origin-width=\"3000\" data-origin-height=\"1694\"><span data-url=\"https://blog.kakaocdn.net/dn/xRFSk/btr1f2pMbPC/A21XkZ0JtNBRGisy3Vz3Nk/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/xRFSk/btr1f2pMbPC/A21XkZ0JtNBRGisy3Vz3Nk/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxRFSk%2Fbtr1f2pMbPC%2FA21XkZ0JtNBRGisy3Vz3Nk%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02245.webp\" data-origin-width=\"3000\" data-origin-height=\"1694\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02250.webp\" data-origin-width=\"3000\" data-origin-height=\"1808\"><span data-url=\"https://blog.kakaocdn.net/dn/dt4PLs/btr01UGp2fm/sqVHjEifCQSRkj9fTQmGNK/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/dt4PLs/btr01UGp2fm/sqVHjEifCQSRkj9fTQmGNK/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdt4PLs%2Fbtr01UGp2fm%2FsqVHjEifCQSRkj9fTQmGNK%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02250.webp\" data-origin-width=\"3000\" data-origin-height=\"1808\"></span></figure>\n" +
                        "<figure class=\"imageblock alignCenter\" data-ke-mobilestyle=\"widthOrigin\" data-filename=\"DSC02279.webp\" data-origin-width=\"3000\" data-origin-height=\"2130\"><span data-url=\"https://blog.kakaocdn.net/dn/z6kl8/btr1fN0sLr8/koNGkcDbeQcYxeLujEDuLk/img.webp\" data-lightbox=\"lightbox\"><img src=\"https://blog.kakaocdn.net/dn/z6kl8/btr1fN0sLr8/koNGkcDbeQcYxeLujEDuLk/img.webp\" srcset=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&amp;fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fz6kl8%2Fbtr1fN0sLr8%2FkoNGkcDbeQcYxeLujEDuLk%2Fimg.webp\" onerror=\"this.onerror=null; this.src='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png'; this.srcset='//t1.daumcdn.net/tistory_admin/static/images/no-image-v1.png';\" data-filename=\"DSC02279.webp\" data-origin-width=\"3000\" data-origin-height=\"2130\"></span></figure>\n" +
                        "<p></p>\n" +
                        "<p data-ke-size=\"size16\">2023년&nbsp;3월&nbsp;1일</p>\n" +
                        "<p data-ke-size=\"size16\">오전에 미세먼지때문에 가시거리가 좋지 않았다.</p>\n" +
                        "<p data-ke-size=\"size16\">오후에 햇볕이 들어와 나가봤다.</p></div>")
                .preview("오전에 미세먼지때문에 가시거리가 좋지 않았다. 오후에 햇볕이 들어와 나가봤다...")
                .build();
        return post1;
    }
}
