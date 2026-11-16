#!/usr/bin/env bash
# 把 work/phone/ios 推到 music-remote-control 的 phone-ios 分支，触发 iOS 云构建。
#
# 为什么单独一个分支：这份 iOS 工程和 main 上的 ios/ 是同源的两个版本线，
# 混在一起会让 main 的包和手机端互相顶。分支里只放构建需要的三样：
#   work/phone/ios/**、.github/workflows/ios-phone.yml
# 推上去 GitHub 直接跑 macOS runner，出未签名 IPA 挂在该次运行的 Artifacts 里。
#
# 用法：bash build_ios.sh ["备注"]
set -euo pipefail
cd "$(dirname "$0")"

BRANCH="${BRANCH:-phone-ios}"
REMOTE="${REMOTE:-https://github.com/boderline7/music-remote-control.git}"
NOTE="${1:-}"

VER=$(grep -m1 'MARKETING_VERSION' work/phone/ios/project.yml | sed 's/.*"\(.*\)".*/\1/')
MSG="iOS 手机端 ${VER}"
[ -n "$NOTE" ] && MSG="$MSG：$NOTE"

# 上一版作为父提交，避免每次都推一条无父提交的历史
PARENT=$(git ls-remote "$REMOTE" "refs/heads/$BRANCH" 2>/dev/null | cut -f1 || true)

IDX=$(mktemp -u)
GIT_INDEX_FILE="$IDX" git read-tree --empty
GIT_INDEX_FILE="$IDX" git add -f work/phone/ios .github/workflows/ios-phone.yml
TREE=$(GIT_INDEX_FILE="$IDX" git write-tree)
rm -f "$IDX"

if [ -n "$PARENT" ]; then
    COMMIT=$(git commit-tree "$TREE" -p "$PARENT" -m "$MSG")
else
    COMMIT=$(git commit-tree "$TREE" -m "$MSG")
fi

git push "$REMOTE" "$COMMIT:refs/heads/$BRANCH" 2>&1 | tail -2

echo
echo "已推送 ${COMMIT:0:8} 到 ${BRANCH}（iOS ${VER}）"
echo "构建与产物：https://github.com/boderline7/music-remote-control/actions"
echo "下载 Artifacts 里的 CarRemote-unsigned-ipa，解压得 CarRemote-unsigned.ipa"
